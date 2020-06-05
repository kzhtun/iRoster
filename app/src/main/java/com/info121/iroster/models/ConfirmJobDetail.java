package com.info121.iroster.models;

public class ConfirmJobDetail {
    String claster;
    String id;
    String siteName;
    String address;
    String shift;

    String post;

    String date;
    String time;
    String status;
    String officer;

    String guard;
    String guardId;

    public ConfirmJobDetail(String claster, String id, String siteName, String address, String shift, String post, String date, String time, String status, String officer, String guard, String guardId) {
        this.claster = claster;
        this.id = id;
        this.siteName = siteName;
        this.address = address;
        this.shift = shift;
        this.post = post;
        this.date = date;
        this.time = time;
        this.status = status;
        this.officer = officer;
        this.guard = guard;
        this.guardId = guardId;
    }

    public String getClaster() {
        return claster;
    }

    public void setClaster(String claster) {
        this.claster = claster;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }

    public String getGuardId() {
        return guardId;
    }

    public void setGuardId(String guardId) {
        this.guardId = guardId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
