package com.littlepaychallenge.mahmoud.repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeMap;

import com.littlepaychallenge.mahmoud.model.Tap;

public class TapRepository {
    private HashMap<String, TreeMap<LocalDate, TravelHistoryRepository>> tapRepository;

    public TapRepository(){
        this.tapRepository = new HashMap<>();
    }

    public HashMap<String, TreeMap<LocalDate, TravelHistoryRepository>> getTapRepository(){
        return this.tapRepository;
    }

    public TreeMap<LocalDate, TravelHistoryRepository> fetchFromTapRepository(Tap tap){
        String pan = tap.getPan(); //This is used as an ID to fetch from the tree map.
        TreeMap<LocalDate, TravelHistoryRepository> dateMap = this.tapRepository.getOrDefault(pan, new TreeMap<LocalDate, TravelHistoryRepository>());
        return dateMap;
    }

    public void addToTapRepository(Tap tap, TreeMap<LocalDate, TravelHistoryRepository> dateMap){
        String pan = tap.getPan();
        this.tapRepository.put(pan, dateMap);
    }
}
