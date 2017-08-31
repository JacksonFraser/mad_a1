package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.s3529589.mad_a1.Activity.meetingActivities.ScheduleMeetingActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomDatePickerDialogController implements View.OnClickListener {
    private ScheduleMeetingActivity scheduleMeetingActivity;
    private EditText meetingTime;
    public CustomDatePickerDialogController(ScheduleMeetingActivity scheduleMeetingActivity, EditText meetingTime) {
        this.scheduleMeetingActivity = scheduleMeetingActivity;
        this.meetingTime = meetingTime;;
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                scheduleMeetingActivity, android.R.style.Theme_Holo_Light_Dialog_MinWidth, listener, year, month,day);

        // CONVERT FROM CAL TO DATE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        Date date = cal.getTime();

        // Set the hour and minute

        //cal.set(Calendar.HOUR_OF_DAY, 17);
        //cal.set(Calendar.MINUTE, 30);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(date);
        System.out.println(formattedDate);

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        datePickerDialog.show();
    }
}
