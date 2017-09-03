package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.content.Intent;
import android.view.View;

import com.example.s3529589.mad_a1.Activity.meetingActivities.DisplayMeetingActivity;
import com.example.s3529589.mad_a1.Activity.meetingActivities.MeetingMenuActivity;

public class DisplayMeetingController implements View.OnClickListener {
    private MeetingMenuActivity meetingMenuActivity;
    public DisplayMeetingController(MeetingMenuActivity meetingMenuActivity) {
        this.meetingMenuActivity = meetingMenuActivity;
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(meetingMenuActivity, DisplayMeetingActivity.class);
        meetingMenuActivity.startActivity(it);
        meetingMenuActivity.finish();
    }
}
