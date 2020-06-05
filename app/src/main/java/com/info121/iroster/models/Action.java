package com.info121.iroster.models;

public class Action {
    String type;
    String title;
    String subtitle;
    JobDetail jobDetail;


    public Action(String type, String title, String subtitle, JobDetail jobDetail) {
        this.type = type;
        this.title = title;
        this.subtitle = subtitle;
        this.jobDetail = jobDetail;
    }

    public Action(String type, String title, String subtitle) {
        this.type = type;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public JobDetail getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }
}
