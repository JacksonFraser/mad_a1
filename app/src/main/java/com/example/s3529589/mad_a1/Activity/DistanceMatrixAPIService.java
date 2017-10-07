package com.example.s3529589.mad_a1.Activity;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import com.example.s3529589.mad_a1.Model.HttpHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class DistanceMatrixAPIService extends Service {

    final String API_KEY = "AIzaSyCMnEw6U-no-uYyqL8o40N_dV91lc5QldQ";

    double originLat = -37.761785;
    double originLon = 144.962852;

    double friendLat =  -37.823530;
    double friendLon = 144.958095;

    // Coordinates after calculating midway point
    double midwayLat;
    double midwayLon;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        // calculate the midway point between the origin and the destination
        midPoint(originLat, originLon, friendLat, friendLon);

        // get the walking distance
        new getWalkingDistance().execute();

        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void midPoint(double originLat, double originLon, double destinationLat, double destinationLon){
        double midwayLat = (originLat + destinationLat)/2;
        double midwayLon = (originLon + destinationLon)/2;

        //print out long lat
        System.out.println("latitude: " + midwayLat + ", Longitude: " + midwayLon);

        this.midwayLat = midwayLat;
        this.midwayLon = midwayLon;
    }

    private class getWalkingDistance extends AsyncTask<Void, Void, Void> {

        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&" +
                "origins=" + originLat + "," + originLon + "&destinations=" + midwayLat + "," + midwayLon
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
