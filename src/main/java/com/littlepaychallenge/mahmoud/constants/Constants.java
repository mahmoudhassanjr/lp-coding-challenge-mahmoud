package com.littlepaychallenge.mahmoud.constants;

public class Constants {
    public enum TRIP_STATUSES{
        CANCELLED, COMPLETED, INCOMPLETE;
    }

    public enum TAP_TYPES{
        OFF, ON;
    }

    public static final double LOW_FARE = 3.25;
    public static final double MEDIUM_FARE = 5.50;
    public static final double HIGHEST_FARE = 7.30;
    public static final String OUTPUT_FILE_HEADERS = "Started,Finished,DurationSecs,FromStopId,ToStopId,ChargeAmount,CompanyId,BusId,PAN,Status";
}
