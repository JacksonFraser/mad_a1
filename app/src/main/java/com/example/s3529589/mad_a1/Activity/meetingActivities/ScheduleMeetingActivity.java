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
import com.example.s3529589.mad_a1.Model.CustomTimePickDialogController;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

import java.util.Calendar;

public class ScheduleMeetingActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_meeting);

        //Meeting title
        EditText meetingTitleView = (EditText) findViewById(R.id.meeting_title);
        String meetingTitle = meetingTitleView.getText().toString();


        //Meeting start date
        View startDateBtn = findViewById(R.id.start_date_btn);
        final EditText meetingStartDateET = (EditText) findViewById(R.id.start_time);
        String meetingStartDate = meetingStartDateET.getText().toString();
        startDateBtn.setOnClickListener(new CustomDatePickerDialogController(this,meetingStartDateET));

        //Meeting end date
        View endDateBtn = findViewById(R.id.end_date_btn);
        final EditText meetingEndDateET = (EditText) findViewById(R.id.end_time);
        String meetingEndDate = meetingEndDateET.getText().toString();
        endDateBtn.setOnClickListener(new CustomDatePickerDialogController(this,meetingEndDateET));

        //Meeting start time
        View startTimeBtn = findViewById(R.id.start_time_meeting_button);
        startTimeBtn.setOnClickListener(new CustomTimePickDialogController(this,meetingStartDateET));

        //Meeting end time
        View endTimeBtn = findViewById(R.id.end_time_meeting_button);
        endTimeBtn.setOnClickListener(new CustomTimePickDialogController(this,meetingEndDateET));




        View createMeetingBtn = findViewById(R.id.create_meeting_confirm_btn);
        //createMeetingBtn.setOnClickListener(new CreateMeetingController(this,meetingTitle,meetingStartDate,meetingEndDate));


    }
}

