package com.littlepaychallenge.mahmoud.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeMap;

import com.littlepaychallenge.mahmoud.constants.Constants;
import com.littlepaychallenge.mahmoud.model.Tap;
import com.littlepaychallenge.mahmoud.model.Trip;
import com.littlepaychallenge.mahmoud.service.FareService;
import com.littlepaychallenge.mahmoud.service.TapService;

public class TravelHistoryRepository {
    private String pan;
    private LocalDate date;
    private TreeMap<LocalDateTime, Tap> tapRecords;
    private Trip trip;
    private Tap mostRecentTap;
    private boolean tripComplete;
    private boolean tripCancelled;
    private boolean tripIncomplete;
    private String tripStatus;

    private FareService fareService;
    private TapService tapService;

    public TravelHistoryRepository(String pan, LocalDate date) {
        this.pan = pan;
        this.date = date;
        this.tapRecords = new TreeMap<LocalDateTime, Tap>();
        this.tripComplete = false;
        this.tripCancelled = false;
        this.tripIncomplete = true;
        this.trip = null; // There aren't any completed trips at this point yet
        this.tripStatus = Constants.TRIP_STATUSES.INCOMPLETE.toString();

        this.fareService = new FareService();
        this.tapService = new TapService();
    }

    public void addToTapRecords(Tap tap) {
        LocalDateTime tapTime = tap.getDateTimeUTC();

        tapRecords.put(tapTime, tap); //A tap for that particular time is added to the tree map
        this.mostRecentTap = tap; //The most recent tap is tracked for computationally efficient lookup

        if (tapService.checkTapOff(tap, this)) {
            System.out.println("---- TAPPED OFF ----");
        }
    }

    //Calls the fare service to calculate the fare between two taps
    public double calculateFare(Tap previousTap, Tap currentTap){
        return fareService.calculateFare(previousTap, currentTap);
    }   

    public TreeMap<LocalDateTime, Tap> getTapRecords() {
        return this.tapRecords;
    }

    public String getPan() {
        return this.pan;
    }

    public Trip getTrip() {
        return this.trip;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Tap getMostRecentTap() {
        return this.mostRecentTap;
    }

    public boolean isTripComplete() {
        return this.tripComplete;
    }

    public boolean isTripCancelled() {
        return this.tripCancelled;
    }

    public boolean isTripIncomplete() {
        return this.tripIncomplete;
    }

    public void setCancelledTrip(boolean isCancelled){
        this.tripCancelled = isCancelled;
    }

    public void setCompleteTrip(boolean isComplete){
        this.tripComplete = isComplete;
    }

    public void setIncompleteTrip(boolean isIncomplete){
        this.tripIncomplete = isIncomplete;
    }

    public void setTripStatus(String status){
        this.tripStatus = status;
    }

    public void setTrip(Trip trip){
        this.trip = trip;
    }

    public String getTripStatus() {
        return this.tripStatus;
    }

    @Override
    public String toString() {
        return this.pan + " " + this.date + " " + this.tapRecords.toString();
    }
}
