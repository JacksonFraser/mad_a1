package com.example.s3529589.mad_a1.Model;

import android.app.TimePickerDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.s3529589.mad_a1.Activity.meetingActivities.ScheduleMeetingActivity;

import java.util.Calendar;

public class CustomTimePickDialogController implements View.OnClickListener {
    private ScheduleMeetingActivity scheduleMeetingActivity;
    private EditText meetingTime;

    public CustomTimePickDialogController(ScheduleMeetingActivity scheduleMeetingActivity, EditText meetingTime) {
        this.scheduleMeetingActivity = scheduleMeetingActivity;
        this.meetingTime = meetingTime;
    }

    @Override
    public void onClick(View v) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                if(minute >=10) {
                    String time = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                    meetingTime.append(" "+time);
                }
                else{
                    String time = String.valueOf(hourOfDay) + ":0" + String.valueOf(minute);
                    meetingTime.append(" "+time);

                }

            }
        };
        TimePickerDialog t = new TimePickerDialog(scheduleMeetingActivity, android.R.style.Theme_Holo_Light_Dialog_MinWidth, listener, hour,minute, true);
        t.show();

        /*DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String time = String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1);
                meetingTime.setText(time);
            }
        };

        DatePickerDialog d = new DatePickerDialog(
                scheduleMeetingActivity, android.R.style.Theme_Holo_Light_Dialog_MinWidth, listener, year, month,day);

        d.show();*/
    }
}
