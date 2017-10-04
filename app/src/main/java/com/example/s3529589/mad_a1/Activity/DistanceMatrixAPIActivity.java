package com.example.s3529589.mad_a1.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.s3529589.mad_a1.Model.HttpHelper;
import com.example.s3529589.mad_a1.Model.Meeting;
import com.example.s3529589.mad_a1.R;

import org.json.JSONException;
import org.json.JSONObject;


public class DistanceMatrixAPIActivity  {
    final String API_KEY = "AIzaSyCMnEw6U-no-uYyqL8o40N_dV91lc5QldQ";
    private double friendLatitude;
    private double friendLongitude;
    private double gpsLatitude;
    private double gpsLongitude;
    private Meeting meeting;
    private double midwayLat;
    private double midwayLon;


   public DistanceMatrixAPIActivity(Meeting meeting, double latitude, double longitude){
       this.meeting = meeting;
       this.friendLatitude = latitude;
       this.friendLongitude = longitude;
       gpsLatitude = -37.761785;
       gpsLongitude= 144.962852;
   }
    // Original coordinates
    // RMIT to Melbourne Central
    /*
    double originLat = -37.808943;
    double originLon = 144.965117;

    double destinationLat = -37.810428;
    double destinationLon = 144.962915;
    */
    // A1 Bakery to Crown Casino

    public String  midPoint(){
      //  new getWalkingDistance().execute();
        midwayLat = ((gpsLatitude) + (friendLatitude))/2;
        midwayLon = (gpsLongitude + friendLongitude)/2;

        //print out long lat
        System.out.println("latitude: " + midwayLat + ", Longitude: " + midwayLon);

        String meetingLocationString = midwayLat + "," + midwayLon;

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
}
