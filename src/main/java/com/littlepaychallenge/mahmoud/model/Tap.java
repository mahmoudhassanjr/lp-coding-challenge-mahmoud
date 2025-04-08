package com.littlepaychallenge.mahmoud.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.littlepaychallenge.mahmoud.utils.DateUtils;

public class Tap implements Comparable<Tap> {
    private String id;
    private LocalDateTime dateTimeUTC;
    private String tapType;
    private String stopId;
    private String companyId;
    private String busId;
    private String pan;
    private LocalDate date;

    private DateUtils dateUtils = new DateUtils();

    public Tap(String id, String dateTimeUTC, String tapType, String stopId, String companyId, String busId,
            String pan) {

        LocalDateTime convertedDate = dateUtils.processUTCDate(dateTimeUTC); //We convert the date from a string to make it easier to convert and process down the line (e.g. obtaining the durationSecs)
        
        this.id = id;
        this.dateTimeUTC = convertedDate;
        this.tapType = tapType;
        this.stopId = stopId;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
        this.date = convertedDate.toLocalDate();
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalDateTime getDateTimeUTC() {
        return this.dateTimeUTC;
    }

    public String getTapType() {
        return this.tapType;
    }

    public String getStopId() {
        return this.stopId;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public String getBusId() {
        return this.busId;
    }

    public String getPan() {
        return this.pan;
    }

    @Override
    public String toString() {
        return "\nID: " + id
                + "\nDateTimeUTC: "
                + dateTimeUTC
                + "\nTap Type: "
                + tapType
                + "\nStop ID: "
                + stopId
                + "\nCompany ID: "
                + companyId
                + "\nBus ID: "
                + busId
                + "\nPAN: "
                + pan;
    }

    @Override
    public int compareTo(Tap o) {
        if (o == null || !(o instanceof Tap)) {
            throw new IllegalArgumentException("Cannot compare with null or non-Tap object");
        }

        Tap other = (Tap) o;

        return this.dateTimeUTC.compareTo(other.dateTimeUTC);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tap other = (Tap) o;
        return Objects.equals(id, other.id) &&
                Objects.equals(dateTimeUTC, other.dateTimeUTC) &&
                Objects.equals(tapType, other.tapType) &&
                Objects.equals(stopId, other.stopId) &&
                Objects.equals(companyId, other.companyId) &&
                Objects.equals(busId, other.busId) &&
                Objects.equals(pan, other.pan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTimeUTC, tapType, stopId, companyId, busId, pan);
    }
}
