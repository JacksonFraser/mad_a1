package com.example.s3529589.mad_a1.Activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.s3529589.mad_a1.Activity.meetingActivities.DisplayMeetingActivity;

public class MeetingAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // Recieve intent
        String meetingTitle = intent.getStringExtra("meeting_title");

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Friend Finder App")
                .setContentText(meetingTitle + " is soon!")

                // open meeting menu on click
                .setContentIntent(PendingIntent.getActivity(context, 0,
                        new Intent(context, DisplayMeetingActivity.class), PendingIntent.FLAG_UPDATE_CURRENT))

                // click to do something
                .addAction(android.R.drawable.ic_menu_info_details, "Dismiss", null)
                .addAction(android.R.drawable.stat_notify_error, "Cancel", null)
                .addAction(android.R.drawable.ic_menu_edit, "Remind me later", null);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}
