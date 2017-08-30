package com.example.s3529589.mad_a1.Activity.meetingActivities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.s3529589.mad_a1.Controller.meetingControllers.CustomDatePickerDialogController;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

import java.util.Calendar;

public class ScheduleMeetingActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_meeting);

        EditText meetingTitleView = (EditText) findViewById(R.id.meeting_title);
        String meetingTitle = meetingTitleView.getText().toString();

        View startDateBtn = findViewById(R.id.start_date_btn);
        final EditText meetingStartDateET = (EditText) findViewById(R.id.start_time);
        String meetingStartDate = meetingStartDateET.getText().toString();

        View endDateBtn = findViewById(R.id.end_date_btn);
        final EditText meetingEndDateET = (EditText) findViewById(R.id.end_time);
        String meetingEndDate = meetingEndDateET.getText().toString();

        startDateBtn.setOnClickListener(new CustomDatePickerDialogController(this,meetingStartDateET));

        endDateBtn.setOnClickListener(new CustomDatePickerDialogController(this,meetingEndDateET));



        View createMeetingBtn = findViewById(R.id.create_meeting_confirm_btn);
        //createMeetingBtn.setOnClickListener(new CreateMeetingController(this,meetingTitle,meetingStartDate,meetingEndDate));


    }
}

