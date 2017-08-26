package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.s3529589.mad_a1.Activity.MeetingMenuActivity;
import com.example.s3529589.mad_a1.Activity.ScheduleMeetingActivity;

/**
 * Created by s3529589 on 8/26/17.
 */

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
