package com.example.s3529589.mad_a1.Activity.meetingActivities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

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

        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog d = new DatePickerDialog(ScheduleMeetingActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
                d.show();
                d.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       // String meetingStartDate = String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1);
                        meetingStartDateET.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1));

                    }
                });
            }

        });

        endDateBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog d = new DatePickerDialog(ScheduleMeetingActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
                d.show();
                d.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // String meetingStartDate = String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1);
                        meetingEndDateET.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1));

                    }
                });
            }

        });

        View createMeetingBtn = findViewById(R.id.create_meeting_confirm_btn);
        //createMeetingBtn.setOnClickListener(new CreateMeetingController(this,meetingTitle,meetingStartDate,meetingEndDate));


    }
}

