package com.example.android.quakereport;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake implements Serializable {
    private double magnitude;
    private long time;
    private String primaryLocation;
    private String locationOffset;
    private String url;

    public Earthquake(double mag, String loc, long time, String url) {
        setMagnitude(mag);
        setLoc(loc);
        setTime(time);
        setUrl(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPrimaryLocation() {
        return primaryLocation;
    }

    public String getLocationOffset() {
        return locationOffset;
    }

    public void setLoc(String loc) {
        StringBuilder sb = new StringBuilder(loc);
        int i = sb.lastIndexOf(" of ");
        if (i != -1) {
            locationOffset = sb.substring(0, i + 4).trim();
            primaryLocation = sb.substring(i + 4).trim();
        } else {
            locationOffset = "Near the";
            primaryLocation = loc;
        }
    }

    public String getTimeDate() {
        try {
            Date date = new Date(time);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
            return dateFormat.format(date);
        } catch (Error e) {
            Log.e("error in date format", e.getMessage());
        }
        return "this can't be right";
    }

    public String getTimeHour() {
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        return dateFormat.format(date);
    }

    public void setTime(long time) {
        this.time = time;
    }
}
