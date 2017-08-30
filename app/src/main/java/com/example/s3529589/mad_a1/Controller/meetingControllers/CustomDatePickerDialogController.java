package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.s3529589.mad_a1.Activity.meetingActivities.ScheduleMeetingActivity;
import com.example.s3529589.mad_a1.Model.DataSingleton;

import java.util.Calendar;

/**
 * Created by s3529589 on 8/30/17.
 */

public class CustomDatePickerDialogController implements View.OnClickListener {
    private ScheduleMeetingActivity scheduleMeetingActivity;
    private EditText meetingTime;
    public CustomDatePickerDialogController(ScheduleMeetingActivity scheduleMeetingActivity, EditText meetingTime) {
        this.scheduleMeetingActivity = scheduleMeetingActivity;
        this.meetingTime = meetingTime;
    }

    @Override
    public void onClick(View v) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String time = String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1);
                meetingTime.setText(time);
            }
        };

        DatePickerDialog d = new DatePickerDialog(
                scheduleMeetingActivity, android.R.style.Theme_Holo_Light_Dialog_MinWidth, listener, year, month,day);

        d.show();
    }
}