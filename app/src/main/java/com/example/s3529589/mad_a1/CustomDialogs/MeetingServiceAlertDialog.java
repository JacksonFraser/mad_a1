package com.example.s3529589.mad_a1.CustomDialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.s3529589.mad_a1.Model.CalculateWalkingDistance;
import com.example.s3529589.mad_a1.Database.FriendTable;
import com.example.s3529589.mad_a1.Model.Friend;

import java.util.Map;


public class MeetingServiceAlertDialog extends AlertDialog.Builder {
    private Context context;

    public MeetingServiceAlertDialog(Context context) {
        super(context);
        this.context = context;

        final String[] holdOptions = {
                "No", "Yes", "Don't ask again"
        };

        setTitle("You look like you need a meeting. Am I right?");

        setItems(holdOptions, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        noSelected();
                        break;
                    case 1:
                        yesSelected();
                        break;
                    case 2:
                        stopAsking();
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void stopAsking() {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(12);
        noSelected();
    }

    private void noSelected() {
        if (context instanceof Activity)
            ((Activity) (context)).finish();
    }

    private void yesSelected() {
        // context.startService(new Intent(context, DistanceMatrixAPIService.class));
        CalculateWalkingDistance walkingDistance = new CalculateWalkingDistance(((Activity) (context)));
        walkingDistance.start();


        //Friend closestFriend = findClosestFriend(friendWalkingDistanceMap);

        // System.out.println("Closest " +closestFriend.getName());
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
