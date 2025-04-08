package com.littlepaychallenge.mahmoud.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public LocalDateTime processUTCDate(String utcDate) {
        LocalDateTime processedDateTime = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            processedDateTime = LocalDateTime.parse(utcDate, formatter);
            return processedDateTime;
        } catch (Exception e) {
            System.out.println("Failed to process date.");
        }
        
        System.out.println("PROCESSED TIME: " + processedDateTime);
        return processedDateTime;
    }

    public String dateToProcessedString(LocalDateTime date){
        return date.toString().replace("T", " "); //Removes the default timestamp 'T' to more closely match the example output from the spec document.
    }
}
