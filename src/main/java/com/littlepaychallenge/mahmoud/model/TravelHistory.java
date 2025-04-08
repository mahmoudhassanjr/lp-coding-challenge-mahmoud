package com.littlepaychallenge.mahmoud.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeMap;

import com.littlepaychallenge.mahmoud.constants.Constants;
import com.littlepaychallenge.mahmoud.service.FareService;

public class TravelHistory {
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

    public TravelHistory(String pan, LocalDate date) {
        this.pan = pan;
        this.date = date;
        this.tapRecords = new TreeMap<LocalDateTime, Tap>();
        this.tripComplete = false;
        this.tripCancelled = false;
        this.tripIncomplete = true;
        this.trip = null; // There aren't any completed trips at this point yet
        this.tripStatus = Constants.TRIP_STATUSES.INCOMPLETE.toString();

        this.fareService = new FareService();
    }

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

    public String getTripStatus() {
        return this.tripStatus;
    }
}
