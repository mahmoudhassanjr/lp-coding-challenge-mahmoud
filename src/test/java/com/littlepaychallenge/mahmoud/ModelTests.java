package com.littlepaychallenge.mahmoud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.littlepaychallenge.mahmoud.model.Stop;
import com.littlepaychallenge.mahmoud.model.Tap;
import com.littlepaychallenge.mahmoud.model.Trip;


public class ModelTests {
    
    @Test
    public void testCreateTapModel() {
        Tap tapOne = new Tap("1", "22-01-2023 13:00:00", "ON", "Stop1", "Company 1", "Bus37", "5500005555555559");
        
        String expected = "\nID: 1" +
                         ",DateTimeUTC: 2023-01-22T13:00" +
                         ",Tap Type: ON" +
                         ",Stop ID: Stop1" +
                         ",Company ID: Company 1" +
                         ",Bus ID: Bus37" +
                         ",PAN: 5500005555555559\n";
        
        assertEquals(expected, tapOne.toString());
    }

    @Test
    public void testCreateStopModel(){
        Stop stop = new Stop("Stop1");

        String expected = "Stop1 1";

        assertEquals(expected, stop.toString());
    }

    @Test
    public void testCreateTripModel() {
        Trip trip = new Trip(
            "22-01-2023 13:00:00",
            "22-01-2023 13:15:00",
            "900",
            "Stop1",
            "Stop2",
            "$3.25",
            "Company1",
            "Bus37",
            "5500005555555559",
            "COMPLETED"
        );
        
        String expected = "\n22-01-2023 13:00:00,22-01-2023 13:15:00,900,Stop1,Stop2,$3.25,Company1,Bus37,5500005555555559,COMPLETED";
        assertEquals(expected, trip.toString());
    }
}
