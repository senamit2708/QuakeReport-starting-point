package com.example.android.quakereport;

import java.util.ArrayList;

/**
 * Created by senamit on 20/9/17.
 */

public class EarthquakeItems {

    private double magnitude;
    private String location;
    private long time;
//    private long date;


    public EarthquakeItems(double magnitude, String location, long time) {
        this.magnitude = magnitude;
        this.location = location;
        this.time = time;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getTime() {
        return time;
    }

//    public long getDate() {
//        return date;
//    }
//
//    public void setDate(long date) {
//        this.date = date;
//    }
}
