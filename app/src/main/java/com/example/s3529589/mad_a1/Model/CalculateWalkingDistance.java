package com.example.s3529589.mad_a1.Model;

import android.app.Activity;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.example.s3529589.mad_a1.Database.FriendTable;
import com.example.s3529589.mad_a1.Database.MeetingFriendTable;
import com.example.s3529589.mad_a1.Database.MeetingTable;
import com.example.s3529589.mad_a1.Exceptions.InvalidMeetingInput;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalculateWalkingDistance extends AsyncTask<Void, Void, Void> {
    private double currLat;
    private double currLon;
    private double friendLat;
    private double friendLon;
    private String walkingDistance;
    private final String API_KEY = "AIzaSyCMnEw6U-no-uYyqL8o40N_dV91lc5QldQ";
    private Activity context;
    private Map<Friend, Integer> friendWalkingDistanceMap;

    public CalculateWalkingDistance(Activity context) {
        this.currLat = -37.810449;
        this.currLon = 144.962808;
        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        GPSCurrentLocationFinder lf = new GPSCurrentLocationFinder(context);
        Location l = lf.findCurrentLocation();

        currLat = l.getLatitude();
        currLon = l.getLongitude();
        FriendTable friendTable = new FriendTable();
        friendWalkingDistanceMap = new HashMap<>();
        for (Friend f : friendTable.getAllFriends()) {
            friendLat = f.getLat();
            friendLon = f.getLon();

            int walkingDuration = calculateWalkingDistance();

            friendWalkingDistanceMap.put(f, walkingDuration);

        }

        Friend f = findClosestFriend(friendWalkingDistanceMap);

        Date startDate = new Date();
        Calendar currTime = Calendar.getInstance();
        long t = currTime.getTimeInMillis();
        Date endDate = new Date(t + (10 * 60000));

        MeetingTable meetingTable = new MeetingTable();
        MeetingFriendTable meetingFriendTable = new MeetingFriendTable();
        try {
            Meeting m = new Meeting("Meeting with " + f.getName(), startDate, endDate);
            CalculateMeetingLocation midPoint = new CalculateMeetingLocation(context, f.getLat(), f.getLon());
            m.setLocation(midPoint.getMidPoint());
            meetingTable.addMeeting(m);
            MeetingFriend meetingFriend = new MeetingFriend(m.getId(), f.getId());

        } catch (InvalidMeetingInput e) {
            e.printStackTrace();
        }


        return null;
    }


    public String getWalkingDistance() {
        return walkingDistance;
    }

    public void start() {
        this.execute();
    }

    public void setFriendLat(double friendLat) {
        this.friendLat = friendLat;
    }

    public void setFriendLon(double friendLon) {
        this.friendLon = friendLon;
    }


    public void setWalkingDistance(String walkingDistance) {
        this.walkingDistance = walkingDistance;
    }

    private int calculateWalkingDistance() {
        HttpHelper httpHandler = new HttpHelper();
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&" +
                "origins=" + currLat + "," + currLon + "&destinations=" + friendLat + "," + friendLon
                + "&mode=walking" + "&key=" + API_KEY;

        String jsonString = httpHandler.makeServiceCall(url);
        try {
            JSONObject jsonDistance = new JSONObject(jsonString)
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("duration");

            walkingDistance = jsonDistance.get("value").toString();
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        Log.e("RESULTS", "Response from url: " + jsonString);

        int dist = Integer.parseInt(walkingDistance);
        return dist;
    }

    private Friend findClosestFriend(Map<Friend, Integer> friendWalkingDistanceMap) {

        int lowestVal = 10000;
        Friend f = new Friend();
        for (Map.Entry<Friend, Integer> entry : friendWalkingDistanceMap.entrySet()) {
            if (entry.getValue() < lowestVal) {
                lowestVal = entry.getValue();
                f = findFriendFromDB(entry.getKey());
            }

        }
        return f;

    }

    private Friend findFriendFromDB(Friend friend) {
        FriendTable friendTable = new FriendTable();
        for (Friend f : friendTable.getAllFriends()) {
            if (f.getId().equals(friend.getId())) {
                return f;
            }
        }
        return null;
    }

}

