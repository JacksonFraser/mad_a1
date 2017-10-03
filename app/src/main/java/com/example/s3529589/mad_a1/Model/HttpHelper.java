package com.example.s3529589.mad_a1.Model;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {

    private static final String TAG = HttpHelper.class.getName();

    public String makeServiceCall(String reqUrl) {
        String jsonResponse = null;

        try {
            // get the data from the url and convert it to string
            URL url = new URL(reqUrl);
            HttpURLConnection distanceMatrixAPI = (HttpURLConnection) url.openConnection();
            distanceMatrixAPI.setRequestMethod("GET");
            InputStream jsonData = new BufferedInputStream(distanceMatrixAPI.getInputStream());
            jsonResponse = convertToString(jsonData);

        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        }
        return jsonResponse;
    }

    private String convertToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        //append line to stringBuilder
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}