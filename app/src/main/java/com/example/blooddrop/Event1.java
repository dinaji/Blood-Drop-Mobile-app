package com.example.blooddrop;

public class Event1 {

    private String event;
    private String date;
    private String time;



    public Event1() {
    }

    public Event1(String event, String date, String time, String venue) {
        this.event = event;
        this.date = date;
        this.time = time;
        this.venue = venue;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    private String venue;
}
