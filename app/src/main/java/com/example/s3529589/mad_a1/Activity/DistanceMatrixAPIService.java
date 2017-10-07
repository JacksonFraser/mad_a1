package com.example.s3529589.mad_a1.Activity;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import com.example.s3529589.mad_a1.Model.HttpHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class DistanceMatrixAPIService {
    private double currLat;
    private double currLon;
    private double friendLat;
    private double friendLon;
    public  String walkingDistance;

    final String API_KEY = "AIzaSyCMnEw6U-no-uYyqL8o40N_dV91lc5QldQ";
    public DistanceMatrixAPIService(){
        this.currLat = -37.810449;
        this.currLon = 144.962808;
    }
    // Coordinates after calculating midway point




    private class GetWalkingDistance extends AsyncTask<Void, Void, Void> {

        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&" +
                "origins=" + currLat + "," + currLon + "&destinations=" + friendLat + "," + friendLon
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

    private void getDistance(String jsonString) throws JSONException {
        JSONObject jsonDistance = new JSONObject(jsonString)
                .getJSONArray("rows")
                .getJSONObject(0)
                .getJSONArray("elements")
                .getJSONObject(0)
                .getJSONObject("duration");

        setWalkingDistance(jsonDistance.get("value").toString());
        System.out.println("walking " + walkingDistance);
    }

    public String getWalkingDistance(){
        return walkingDistance;
    }

    public void setFriendLat(double friendLat) {
        this.friendLat = friendLat;
    }

    public void setFriendLon(double friendLon) {
        this.friendLon = friendLon;
    }

    public void startTheThing(DistanceMatrixAPIService dmAPI){
        new GetWalkingDistance().execute();

    }
    public void setWalkingDistance(String walkingDistance){
        this.walkingDistance = walkingDistance;
    }

}
