package com.littlepaychallenge.mahmoud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.littlepaychallenge.mahmoud.model.Tap;
import com.littlepaychallenge.mahmoud.service.FareService;


public class FareServiceTests {
    
    @Test
    public void testCalculateStop1toStop2Fare() { 
        FareService fareService = new FareService();
        Tap tapOne = new Tap("1", "22-01-2023 13:00:00", "ON", "Stop1", "Company 1", "Bus37", "5500005555555559");
        Tap tapTwo = new Tap("2", "22-01-2023 13:05:00", "OFF", "Stop2", "Company 1", "Bus37", "5500005555555559");

        double fare = fareService.calculateFare(tapOne, tapTwo);

        assertEquals(3.25, fare, 0);
    }

    @Test
    public void testCalculateStop2toStop1Fare() { 
        FareService fareService = new FareService();
        Tap tapOne = new Tap("1", "22-01-2023 13:00:00", "ON", "Stop2", "Company 1", "Bus37", "5500005555555559");
        Tap tapTwo = new Tap("2", "22-01-2023 13:05:00", "OFF", "Stop1", "Company 1", "Bus37", "5500005555555559");

        double fare = fareService.calculateFare(tapOne, tapTwo);

        assertEquals(3.25, fare, 0);
    }

    @Test
    public void testCalculateStop2toStop3Fare() { 
        FareService fareService = new FareService();
        Tap tapOne = new Tap("1", "22-01-2023 13:00:00", "ON", "Stop2", "Company 1", "Bus37", "5500005555555559");
        Tap tapTwo = new Tap("2", "22-01-2023 13:05:00", "OFF", "Stop3", "Company 1", "Bus37", "5500005555555559");

        double fare = fareService.calculateFare(tapOne, tapTwo);

        assertEquals(5.50, fare, 0);
    }

    @Test
    public void testCalculateStop3toStop2Fare() { 
        FareService fareService = new FareService();
        Tap tapOne = new Tap("1", "22-01-2023 13:00:00", "ON", "Stop3", "Company 1", "Bus37", "5500005555555559");
        Tap tapTwo = new Tap("2", "22-01-2023 13:05:00", "OFF", "Stop2", "Company 1", "Bus37", "5500005555555559");

        double fare = fareService.calculateFare(tapOne, tapTwo);

        assertEquals(5.50, fare, 0);
    }

    @Test
    public void testCalculateStop1toStop3Fare() { 
        FareService fareService = new FareService();
        Tap tapOne = new Tap("1", "22-01-2023 13:00:00", "ON", "Stop1", "Company 1", "Bus37", "5500005555555559");
        Tap tapTwo = new Tap("2", "22-01-2023 13:05:00", "OFF", "Stop3", "Company 1", "Bus37", "5500005555555559");

        double fare = fareService.calculateFare(tapOne, tapTwo);

        assertEquals(7.30, fare, 0);
    }

    @Test
    public void testCalculateStop3toStop1Fare() { 
        FareService fareService = new FareService();
        Tap tapOne = new Tap("1", "22-01-2023 13:00:00", "ON", "Stop3", "Company 1", "Bus37", "5500005555555559");
        Tap tapTwo = new Tap("2", "22-01-2023 13:05:00", "OFF", "Stop1", "Company 1", "Bus37", "5500005555555559");

        double fare = fareService.calculateFare(tapOne, tapTwo);

        assertEquals(7.30, fare, 0);
    }

    @Test
    public void testCalculateStop1toStop1Fare() { 
        FareService fareService = new FareService();
        Tap tapOne = new Tap("1", "22-01-2023 13:00:00", "ON", "Stop1", "Company 1", "Bus37", "5500005555555559");
        Tap tapTwo = new Tap("2", "22-01-2023 13:05:00", "OFF", "Stop1", "Company 1", "Bus37", "5500005555555559");

        double fare = fareService.calculateFare(tapOne, tapTwo);

        assertEquals(0.00, fare, 0);
    }

    @Test
    public void testCalculateStop2toStop2Fare() { 
        FareService fareService = new FareService();
        Tap tapOne = new Tap("1", "22-01-2023 13:00:00", "ON", "Stop2", "Company 1", "Bus37", "5500005555555559");
        Tap tapTwo = new Tap("2", "22-01-2023 13:05:00", "OFF", "Stop2", "Company 1", "Bus37", "5500005555555559");

        double fare = fareService.calculateFare(tapOne, tapTwo);

        assertEquals(0.00, fare, 0);
    }

    @Test
    public void testCalculateStop3toStop3Fare() { 
        FareService fareService = new FareService();
        Tap tapOne = new Tap("1", "22-01-2023 13:00:00", "ON", "Stop3", "Company 1", "Bus37", "5500005555555559");
        Tap tapTwo = new Tap("2", "22-01-2023 13:05:00", "OFF", "Stop3", "Company 1", "Bus37", "5500005555555559");

        double fare = fareService.calculateFare(tapOne, tapTwo);

        assertEquals(0.00, fare, 0);
    }
}
