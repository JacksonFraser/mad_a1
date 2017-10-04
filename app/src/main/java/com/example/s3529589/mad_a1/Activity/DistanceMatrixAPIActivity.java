package com.example.s3529589.mad_a1.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.s3529589.mad_a1.Model.HttpHelper;
import com.example.s3529589.mad_a1.R;

import org.json.JSONException;
import org.json.JSONObject;


public class DistanceMatrixAPIActivity extends AppCompatActivity {
    final String API_KEY = "AIzaSyCMnEw6U-no-uYyqL8o40N_dV91lc5QldQ";

    // Original coordinates
    // RMIT to Melbourne Central
    /*
    double originLat = -37.808943;
    double originLon = 144.965117;

    double destinationLat = -37.810428;
    double destinationLon = 144.962915;
    */

    // A1 Bakery to Crown Casino
    double originLat = -37.761785;
    double originLon = 144.962852;

    double destinationLat =  -37.823530;
    double destinationLon = 144.958095;

    // Coordinates after calculating midway point
    double midwayLat;
    double midwayLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distance_activity);

        // calculate the midway point between the origin and the destination
        midPoint(originLat, originLon, destinationLat, destinationLon);

        // get the walking distance
        new getWalkingDistance().execute();
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
