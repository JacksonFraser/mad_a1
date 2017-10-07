package com.example.s3529589.mad_a1.CustomDialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.view.WindowManager;

import com.example.s3529589.mad_a1.Activity.DistanceMatrixAPIService;
import com.example.s3529589.mad_a1.Activity.LocationFinder;
import com.example.s3529589.mad_a1.Database.FriendTable;
import com.example.s3529589.mad_a1.Model.Friend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CustomMeetingServiceAlertDialog extends AlertDialog.Builder {
    private Context context;

    public CustomMeetingServiceAlertDialog(Context context) {
        super(context);
        this.context = context;

        final String[] holdOptions = {
                "No", "Yes"
        };

        setTitle("You look like you need a meeting. Am I right?");

        setItems(holdOptions, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        noSelected();
                        break;
                    case 1:
                        yesSelected();
                        break;
                }
            }
        });

    }

    private void noSelected() {
        if(context instanceof Activity)
            ((Activity)(context)).finish();
    }

    private void yesSelected(){
       // context.startService(new Intent(context, DistanceMatrixAPIService.class));

        LocationFinder lf = new LocationFinder(((Activity)(context)));
        Location l = lf.findCurrentLocation();
        FriendTable friendTable = new FriendTable();
        Map<Friend, Integer> friendWalkingDistanceMap = new HashMap<>();
        for(Friend f : friendTable.getAllFriends()){
            DistanceMatrixAPIService dmAPI = new DistanceMatrixAPIService();
            dmAPI.setFriendLat(f.getLat());
            dmAPI.setFriendLon(f.getLon());
            dmAPI.startTheThing(dmAPI);

            System.out.println("this is walking "+dmAPI.getWalkingDistance()
            );

            int walkingDuration = Integer.parseInt(dmAPI.getWalkingDistance());
            friendWalkingDistanceMap.put(f,walkingDuration);
        }

        //Friend closestFriend = findClosestFriend(friendWalkingDistanceMap);

       // System.out.println("Closest " +closestFriend.getName());


    }

    private Friend findClosestFriend(Map<Friend, Integer> friendWalkingDistanceMap) {

        int lowestVal = 10000;
        Friend f = new Friend();
        for(Map.Entry<Friend, Integer> entry : friendWalkingDistanceMap.entrySet()){
            if(entry.getValue() < lowestVal) {
                lowestVal = entry.getValue();
                f = findFriendFromDB(entry.getKey());
            }

        }
        return f;

    }

    private Friend findFriendFromDB(Friend friend) {
        FriendTable friendTable = new FriendTable();
        for(Friend f : friendTable.getAllFriends()){
            if(f.getId().equals(friend.getId())){
                return f;
            }
        }
        return null;
    }
}
