package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.app.TimePickerDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.s3529589.mad_a1.Activity.meetingActivities.ScheduleMeetingActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FinishMeetingTimeController implements View.OnClickListener {
    private ScheduleMeetingActivity scheduleMeetingActivity;
    private TextView meetingTime;

    public FinishMeetingTimeController(ScheduleMeetingActivity scheduleMeetingActivity, TextView meetingTime) {
        this.scheduleMeetingActivity = scheduleMeetingActivity;
        this.meetingTime = meetingTime;
    }

    @Override
    public void onClick(View v) {
        final Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                Date finishTime = cal.getTime();

                meetingTime.setText(finishTime.toString());

                /*
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm aaa");
                String labelTime = formatter.format(finishTime);

                meetingTime.setText(labelTime);
                */
            }
        };
        TimePickerDialog t = new TimePickerDialog(scheduleMeetingActivity, android.R.style.Theme_Holo_Light_Dialog_MinWidth, listener, hour,minute, true);
        t.show();
    }
}
