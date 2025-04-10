package com.littlepaychallenge.mahmoud.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.littlepaychallenge.mahmoud.constants.Constants;
import com.littlepaychallenge.mahmoud.constants.Constants.TAP_TYPES;
import com.littlepaychallenge.mahmoud.constants.Constants.TRIP_STATUSES;
import com.littlepaychallenge.mahmoud.model.Tap;
import com.littlepaychallenge.mahmoud.model.Trip;
import com.littlepaychallenge.mahmoud.repository.TravelHistoryRepository;
import com.littlepaychallenge.mahmoud.utils.DateUtils;

public class TapService {
    private FareService fareService = new FareService();
    private FileService fileService = new FileService();
    private DateUtils dateUtils = new DateUtils();

    public boolean checkTapOff(Tap currentTap, TravelHistoryRepository travelHistoryRepository) {
        boolean isTapOff = false;
        TreeMap<LocalDateTime, Tap> tapRecords = travelHistoryRepository.getTapRecords(); //All the taps for a given PAN at a specific date
        int travelHistoryRepositorySize = travelHistoryRepository.getTapRecords().size();

        if(travelHistoryRepositorySize >= 2){
                Tap previousTap = (Tap) tapRecords.values().toArray()[tapRecords.size() - 2]; //Gets the last tap before the one that's currently being processed
                String previousTapType = previousTap.getTapType();
                String currentTapType = currentTap.getTapType();

                //Checks if a tap on has a corresponding tap off. This is by checking if the previous tap is 'ON' and the current tap is 'OFF'
                //Key assumptions being made here are that (1) a user can only be in 1 bus between 2 taps and (2) The intersection of BusID/Tap date/A corresponding tap off is the only instance in which a trip is completed
                //Another major assumption is that, similar to the Myki system here in Victoria, once a trip is completed, the daily fare will be capped to that completed trip so there will not be a need to process multiple completed trips as subsequent trips aren't counted in the daily total
                boolean hasCorrespondingTap = previousTapType.equalsIgnoreCase(TAP_TYPES.ON.toString())
                        && currentTapType.equalsIgnoreCase(TAP_TYPES.OFF.toString())
                        && currentTap.getBusId().equals(previousTap.getBusId());

                if (hasCorrespondingTap) {
                    isTapOff = true;

                    //Checks for a cancelled trip if a user taps off at the same stop.
                    if (previousTap.getStopId().equalsIgnoreCase(currentTap.getStopId())) {
                        travelHistoryRepository.setCancelledTrip(true);
                        travelHistoryRepository.setTripStatus(TRIP_STATUSES.CANCELLED.toString());

                        double fare = fareService.calculateFare(previousTap, currentTap);
                        System.out.println("\nFARE: " + fare + "\n");

                        createTrip(currentTap, previousTap, fare, travelHistoryRepository);
                    } else {
                        travelHistoryRepository.setCompleteTrip(true);
                        travelHistoryRepository.setTripStatus(TRIP_STATUSES.COMPLETED.toString());

                        System.out.println("---TRIP COMPLETED---");
                        double fare = fareService.calculateFare(previousTap, currentTap);
                        System.out.println("\nFARE: " + fare + "\n");

                        createTrip(currentTap, previousTap, fare, travelHistoryRepository); //Trip marked as complete since the taps were at different stops but in the same bus on the same date
                    }
                }
            } else {
                return isTapOff;
            }

        return isTapOff;
    }

    //Creates a trip based on the tap and fare data
    private void createTrip(Tap endTap, Tap startTap, double fare, TravelHistoryRepository travelHistoryRepository) {
        LocalDateTime finished = endTap.getDateTimeUTC();
        LocalDateTime started = startTap.getDateTimeUTC();
        long durationSecs = ChronoUnit.SECONDS.between(started, finished);

        String fromStopId = startTap.getStopId();
        String toStopId = endTap.getStopId();
        String chargeAmount = "$" + Double.toString(fare);
        String companyId = startTap.getCompanyId();
        String busId = startTap.getBusId();
        String pan = startTap.getPan();
        String status = travelHistoryRepository.getTripStatus();

        Trip newTrip = new Trip(dateUtils.dateToProcessedString(started), dateUtils.dateToProcessedString(finished), Long.toString(durationSecs), fromStopId,
                toStopId, chargeAmount, companyId, busId, pan, status);
        
        travelHistoryRepository.setTrip(newTrip);
    }

    //Trips will be created for all incomplete trips after all other trips have been processed.
    //TODO: Combine 'createTrip' and 'buildIncompleteTrip' to keep things 'DRY'
    //TODO: I should look at ways of storing these incomplete trips during the initial pass through the data. However, this is difficult because all trips start off a 'Incomplete' until a tap off is performed
    public Trip buildIncompleteTrip(Tap startTap, TravelHistoryRepository travelHistoryRepository){
        LocalDateTime started = startTap.getDateTimeUTC();
        String finished = "N/A"; //Trip was incomplete so there's no finished timestamp
        String durationSecs = "N/A"; //Trip was incomplete so there's no duration between start and finish

        String fromStopId = startTap.getStopId();
        String toStopId = startTap.getStopId();
        String chargeAmount = "$" + Double.toString(Constants.HIGHEST_FARE); //Highest fare charged for incomplete trips
        String companyId = startTap.getCompanyId();
        String busId = startTap.getBusId();
        String pan = startTap.getPan();
        String status = travelHistoryRepository.getTripStatus();

        Trip newTrip = new Trip(dateUtils.dateToProcessedString(started), finished, durationSecs, fromStopId,
                toStopId, chargeAmount, companyId, busId, pan, status);
        
        travelHistoryRepository.setTrip(newTrip);

        System.out.println("TRIP: " + newTrip);

        return newTrip;
    }

    public void getIncompleteTrips(HashMap<String, TreeMap<LocalDate, TravelHistoryRepository>> tapRepository,
            TreeMap<String, Trip> tripRepository){
                for (Entry<String, TreeMap<LocalDate, TravelHistoryRepository>> tap : tapRepository.entrySet()) {
                    TreeMap<LocalDate, TravelHistoryRepository> history = tap.getValue();
        
                    for (TravelHistoryRepository travelRepository : history.values()) {
                        if (travelRepository.getTripStatus().equalsIgnoreCase(TRIP_STATUSES.INCOMPLETE.toString())) {
                            Tap mostRecentTap = travelRepository.getMostRecentTap();
                            Trip incompleteTrip = buildIncompleteTrip(mostRecentTap, travelRepository);
                            tripRepository.put(mostRecentTap.getDate().toString(), incompleteTrip);
                        }
                    }
                }    
         
        fileService.writeToFile(tripRepository, "output.csv"); //Final output written to file.
    }
}
