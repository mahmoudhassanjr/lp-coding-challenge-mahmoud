package com.littlepaychallenge.mahmoud.controller;

import java.util.ArrayList;

import com.littlepaychallenge.mahmoud.model.Tap;
import com.littlepaychallenge.mahmoud.repository.TapRepository;
import com.littlepaychallenge.mahmoud.repository.TripRepository;
import com.littlepaychallenge.mahmoud.service.FileService;
import com.littlepaychallenge.mahmoud.service.TapService;

import java.util.TreeMap;
import java.time.LocalDate;
import com.littlepaychallenge.mahmoud.repository.TravelHistoryRepository;
import com.littlepaychallenge.mahmoud.model.Trip;

public class MainController {
    private static FileService fileService = new FileService();
    private static TapService tapService = new TapService();
    private static TapRepository tapRepository = new TapRepository();
    private static TripRepository tripRepository = new TripRepository();
    
    public static void initiateTapProcessing(){
        ArrayList<Tap> taps = readFile();
        System.out.println("TAPS SIZE: " + taps.size());

        for(Tap tap : taps){
            System.out.println("Tap: " + tap.toString());
            TreeMap<LocalDate, TravelHistoryRepository> dateMap = tapRepository.fetchFromTapRepository(tap); //Retrieves taps from a specific date
            TravelHistoryRepository travelHistoryRepository = dateMap.getOrDefault(tap.getDate(), new TravelHistoryRepository(tap.getPan(), tap.getDate()));
            travelHistoryRepository.addToTapRecords(tap);

            if(travelHistoryRepository.isTripComplete() || travelHistoryRepository.isTripCancelled()){
                Trip completedTrip = travelHistoryRepository.getTrip();
                tripRepository.addToTripRepository(completedTrip);
            }else {
                System.out.println("Incomplete trip: " + travelHistoryRepository.getTrip());
            }
            
            dateMap.put(tap.getDate(), travelHistoryRepository);
            tapRepository.addToTapRepository(tap, dateMap);
            displayTaps();
            tapService.getIncompleteTrips(tapRepository.getTapRepository(), tripRepository.getTripRepository());
        }
    }

    private static void displayTaps(){
      System.out.println("Displaying taps");
      System.out.println(tapRepository.getTapRepository().values().toString());
    }

    private static ArrayList<Tap> readFile(){
        fileService = new FileService();

        ArrayList<Tap> taps = fileService.readFile("taps.csv");

        return taps;
    }
}
