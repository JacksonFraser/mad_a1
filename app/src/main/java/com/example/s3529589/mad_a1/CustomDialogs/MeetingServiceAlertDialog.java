package com.example.s3529589.mad_a1.CustomDialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.s3529589.mad_a1.Model.CalculateWalkingDistance;


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
        CalculateWalkingDistance walkingDistance = new CalculateWalkingDistance(((Activity) (context)));
        walkingDistance.start();

        noSelected();
    }

}
