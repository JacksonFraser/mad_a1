package com.example.s3529589.mad_a1.Activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.s3529589.mad_a1.Activity.meetingActivities.ScheduleMeetingActivity;
import com.example.s3529589.mad_a1.Model.HttpHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DistanceMatrixAPIActivity {
    final String API_KEY = "AIzaSyCMnEw6U-no-uYyqL8o40N_dV91lc5QldQ";
    private double friendLatitude;
    private double friendLongitude;
    private double gpsLatitude;
    private double gpsLongitude;
    private double midwayLat;
    private double midwayLon;

    private LocationManager mLocationManager;
    private Location location;
    private ScheduleMeetingActivity context;


    public DistanceMatrixAPIActivity(ScheduleMeetingActivity context, double latitude, double longitude) {
        this.friendLatitude = latitude;
        this.friendLongitude = longitude;
        gpsLatitude = -37.761785;
        gpsLongitude = 144.962852;
        this.context = context;

        location = findCurrentLocation();
        System.out.println("INSIDE THE CONSTRUCTOR " + location.getLatitude());
        System.out.println("INSIDE THE CONSTRUCTOR " + location.getLongitude());

    }

    public String midPoint() {
        //  new getWalkingDistance().execute();
        midwayLat = ((location.getLatitude()) + (friendLatitude)) / 2;
        midwayLon = (location.getLongitude() + friendLongitude) / 2;

        //print out long lat
        System.out.println("latitude: " + midwayLat + ", Longitude: " + midwayLon);

        String meetingLocationString = String.valueOf(midwayLat).substring(0, 8) + ", " + String.valueOf(String.valueOf(midwayLon).substring(0, 8));

        return meetingLocationString;
    }

    private class getWalkingDistance extends AsyncTask<Void, Void, Void> {

        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&" +
                "origins=" + gpsLatitude + "," + gpsLongitude + "&destinations=" + midwayLat + "," + midwayLon
                + "&mode=walking" + "&key=" + API_KEY;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHelper httpHandler = new HttpHelper();

            String jsonString = httpHandler.makeServiceCall(url);
            try {
                getDistance(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("RESULTS", "Response from url: " + jsonString);

            return null;
        }
    }

    private String getDistance(String jsonString) throws JSONException {
        JSONObject jsonDistance = new JSONObject(jsonString)
                .getJSONArray("rows")
                .getJSONObject(0)
                .getJSONArray("elements")
                .getJSONObject(0)
                .getJSONObject("distance");

        String distance = jsonDistance.get("value").toString();

        System.out.println(distance);
        return distance;
    }

    private Location findCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            }
        }

        mLocationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }

        return bestLocation;
    }

}
