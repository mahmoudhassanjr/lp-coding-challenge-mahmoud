package com.littlepaychallenge.mahmoud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.littlepaychallenge.mahmoud.model.Tap;
import com.littlepaychallenge.mahmoud.model.Trip;
import com.littlepaychallenge.mahmoud.repository.TravelHistoryRepository;


public class TapServiceTests {
    
    @Test
    public void testTapOffCompletedTrip() {
        Tap tapOne = new Tap("1", "22-01-2023 13:00:00", "ON", "Stop1", "Company 1", "Bus37", "5500005555555559");
        Tap tapTwo = new Tap("2", "22-01-2023 13:05:00", "OFF", "Stop2", "Company 1", "Bus37", "5500005555555559");

        TravelHistoryRepository travelHistoryRepository = new TravelHistoryRepository(tapOne.getPan(), tapOne.getDate());
        travelHistoryRepository.addToTapRecords(tapOne);
        travelHistoryRepository.addToTapRecords(tapTwo);

        Trip completedTrip = travelHistoryRepository.getTrip();

        String expected = "\n2023-01-22 13:05,2023-01-22 13:00,300,Stop2,Stop1,$3.25,Company 1,Bus37,5500005555555559,COMPLETED";

        assertEquals(expected, completedTrip.toString());
    }

    @Test
    public void testTapOffCancelledTrip() {
        Tap tapOne = new Tap("1", "22-01-2023 13:00:00", "ON", "Stop1", "Company 1", "Bus37", "5500005555555559");
        Tap tapTwo = new Tap("2", "22-01-2023 13:05:00", "OFF", "Stop1", "Company 1", "Bus37", "5500005555555559");

        TravelHistoryRepository travelHistoryRepository = new TravelHistoryRepository(tapOne.getPan(), tapOne.getDate());
        travelHistoryRepository.addToTapRecords(tapOne);
        travelHistoryRepository.addToTapRecords(tapTwo);

        Trip completedTrip = travelHistoryRepository.getTrip();

        String expected = "\n2023-01-22 13:05,2023-01-22 13:00,300,Stop1,Stop1,$0.0,Company 1,Bus37,5500005555555559,CANCELLED";

        assertEquals(expected, completedTrip.toString());
    }

    //Incomplete trips are accounted for after all other trips have been processed.
}
