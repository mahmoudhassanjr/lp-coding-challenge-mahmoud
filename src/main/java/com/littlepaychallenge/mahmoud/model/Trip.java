package com.littlepaychallenge.mahmoud.model;

//This will be used in the output file.
public class Trip {
    private String started;
    private String finished;
    private String durationSecs;
    private String fromStopId;
    private String toStopId;
    private String chargeAmount;
    private String companyId;
    private String busId;
    private String pan;
    private String status;

    public Trip(String started, String finished, String durationSecs, String fromStopId, String toStopId,
            String chargeAmount, String companyId, String busId, String pan, String status) {
        this.started = started;
        this.finished = finished;
        this.durationSecs = durationSecs;
        this.fromStopId = fromStopId;
        this.toStopId = toStopId;
        this.chargeAmount = chargeAmount;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
        this.status = status;
    }

    public String getStarted() {
        return this.started;
    }

    @Override
    public String toString() {
        return "\n" + this.started + "," + this.finished + "," + this.durationSecs + "," + this.fromStopId + ","
                + this.toStopId + "," + this.chargeAmount + "," + this.companyId + "," + this.busId + "," + this.pan
                + "," + this.status;
    }
}
