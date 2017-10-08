package com.example.s3529589.mad_a1.Model;

import android.app.Activity;
import android.location.Location;

public class CalculateMeetingLocation {
    private double friendLatitude;
    private double friendLongitude;
    private double midwayLat;
    private double midwayLon;
    private Location location;
    private Activity context;

    public CalculateMeetingLocation(Activity context, double latitude, double longitude) {
        this.friendLatitude = latitude;
        this.friendLongitude = longitude;
        this.context = context;

        GPSCurrentLocationFinder lf = new GPSCurrentLocationFinder(context);
        location = lf.findCurrentLocation();
    }

    public String getMidPoint() {
        midwayLat = ((location.getLatitude()) + (friendLatitude)) / 2;
        midwayLon = (location.getLongitude() + friendLongitude) / 2;

        // print out long lat
        System.out.println("latitude: " + midwayLat + ", Longitude: " + midwayLon);

        String meetingLocationString = String.valueOf(midwayLat).substring(0, 8) + ", " + String.valueOf(String.valueOf(midwayLon).substring(0, 8));

        return meetingLocationString;
    }
}
