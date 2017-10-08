package com.example.s3529589.mad_a1.Model;

import android.app.Activity;
import android.location.Location;

public class CalculateMidpoint {
    private double friendLatitude;
    private double friendLongitude;
    private double gpsLatitude;
    private double gpsLongitude;
    private double midwayLat;
    private double midwayLon;
    private Location location;
    private Activity context;

    public CalculateMidpoint(Activity context, double latitude, double longitude) {
        this.friendLatitude = latitude;
        this.friendLongitude = longitude;
        this.context = context;

        LocationFinder lf = new LocationFinder(context);
        location = lf.findCurrentLocation();
    }

    public String getMidPoint() {
        //  new getWalkingDistance().execute();
        midwayLat = ((location.getLatitude()) + (friendLatitude)) / 2;
        midwayLon = (location.getLongitude() + friendLongitude) / 2;

        //print out long lat
        System.out.println("latitude: " + midwayLat + ", Longitude: " + midwayLon);

        String meetingLocationString = String.valueOf(midwayLat).substring(0, 8) + ", " + String.valueOf(String.valueOf(midwayLon).substring(0, 8));

        return meetingLocationString;
    }




    /*
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
    */

}
