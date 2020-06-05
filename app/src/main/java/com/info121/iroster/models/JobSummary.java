package com.info121.iroster.models;

public class JobSummary {
    String shift;
    String sector;
    String count;


    public JobSummary(String shift, String sector, String count) {
        this.shift = shift;
        this.sector = sector;
        this.count = count;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
