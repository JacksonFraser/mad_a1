package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.content.Intent;
import android.view.View;

import com.example.s3529589.mad_a1.Activity.meetingActivities.MeetingMenuActivity;
import com.example.s3529589.mad_a1.Activity.meetingActivities.ScheduleMeetingActivity;

public class ScheduleMeetingController implements View.OnClickListener {
    private MeetingMenuActivity meetingMenuActivity;

    public ScheduleMeetingController(MeetingMenuActivity meetingMenuActivity) {
        this.meetingMenuActivity = meetingMenuActivity;
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(meetingMenuActivity, ScheduleMeetingActivity.class);
        meetingMenuActivity.startActivity(it);
    }
}
