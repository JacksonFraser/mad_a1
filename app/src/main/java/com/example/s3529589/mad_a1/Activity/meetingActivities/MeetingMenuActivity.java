package com.example.s3529589.mad_a1.Activity.meetingActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.meetingControllers.DisplayMeetingController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.ScheduleMeetingController;
import com.example.s3529589.mad_a1.R;

public class MeetingMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_menu);

        View scheduleBtn = findViewById(R.id.schedule);
        scheduleBtn.setOnClickListener(new ScheduleMeetingController(this));

        View displayBtn = findViewById(R.id.displayMeetingsBtn);
        displayBtn.setOnClickListener(new DisplayMeetingController(this));
    }
}
