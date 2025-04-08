package com.littlepaychallenge.mahmoud.repository;

import java.util.TreeMap;

import com.littlepaychallenge.mahmoud.model.Tap;
import com.littlepaychallenge.mahmoud.model.Trip;

public class TripRepository {
    private TreeMap<String, Trip> tripRepository;

    public TripRepository(){
        this.tripRepository = new TreeMap<>();
    }

    public TreeMap<String, Trip> getTripRepository(){
        return this.tripRepository;
    }

    public void addToTripRepository(Trip completedTrip){
        tripRepository.put(completedTrip.getStarted(), completedTrip);
    }
}
