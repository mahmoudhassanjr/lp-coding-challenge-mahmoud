package com.littlepaychallenge.mahmoud.model;

public class Stop {
    private String stopId;
    private int stopNumber; //We'll be using the stop number to differentiate between stops

    public Stop(String stopId){
        this.stopId = stopId;
        this.stopNumber = Integer.parseInt(stopId.split("p")[1]); //TODO: I should get rid of this in the future as it's not a scalable and extensible approach for now (what if Stops change from 'Stop' to 'Station'??)
    }

    public String getStopId(){
        return this.stopId;
    }

    public int getStopNumber(){
        return this.stopNumber;
    }

    @Override
    public String toString(){
        return this.stopId + " " + this.stopNumber;
    }
}

