package com.littlepaychallenge.mahmoud.service;

import com.littlepaychallenge.mahmoud.constants.Constants;
import com.littlepaychallenge.mahmoud.model.Stop;
import com.littlepaychallenge.mahmoud.model.Tap;

public class FareService {

    public double calculateFare(Tap previousTap, Tap currentTap){
        System.out.println("Calculating fare.");
        double fare = 0.00;

        Stop previousStop = new Stop(previousTap.getStopId());
        Stop currentStop = new Stop(currentTap.getStopId());

        //Assumption: There are only 3 stops as per the problem statement. --TODO: This needs to be made more extensible so that stops and fares can be added on the fly without having to constantly update this logic for new stops.
        if (previousStop.getStopNumber() == 1 && currentStop.getStopNumber() == 2
                || previousStop.getStopNumber() == 2 && currentStop.getStopNumber() == 1) {

            fare = Constants.LOW_FARE; //When the user has travelled between Stop 1 and 2 or vice versa, they'll pay the smallest amount

        } else if (previousStop.getStopNumber() == 2 && currentStop.getStopNumber() == 3
                || previousStop.getStopNumber() == 3 && currentStop.getStopNumber() == 2) {

            fare = Constants.MEDIUM_FARE; //When the user has travelled between Stop 2 and 3 or vice versa, they'll pay the medium amount

        } else if (previousStop.getStopNumber() == 1 && currentStop.getStopNumber() == 3
                || previousStop.getStopNumber() == 3 && currentStop.getStopNumber() == 1) {

            fare = Constants.HIGHEST_FARE; //When the user has travelled between Stop 1 and 3 they'll pay the highest amount

        }

        System.out.println(previousStop.toString());
        System.out.println(currentStop.toString());

        return fare;
    }
}