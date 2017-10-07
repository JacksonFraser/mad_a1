package com.example.s3529589.mad_a1.Activity.meetingActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.meetingControllers.DisplayMeetingController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.ScheduleMeetingController;
import com.example.s3529589.mad_a1.R;
import com.example.s3529589.mad_a1.Services.ApplicationTrackerSingleton;

public class MeetingMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_menu);
        ApplicationTrackerSingleton.getInstance().setCurrentActivity(this);
        View scheduleBtn = findViewById(R.id.schedule);
        scheduleBtn.setOnClickListener(new ScheduleMeetingController(this));

        View displayBtn = findViewById(R.id.displayMeetingsBtn);
        displayBtn.setOnClickListener(new DisplayMeetingController(this));
    }
}
