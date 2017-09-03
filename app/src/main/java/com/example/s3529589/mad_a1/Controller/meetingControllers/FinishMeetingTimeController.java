package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.app.TimePickerDialog;
import android.view.View;
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

                SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy, h:mm aaa");
                String labelTime = formatter.format(finishTime);

                meetingTime.setText(labelTime);
            }
        };
        TimePickerDialog t = new TimePickerDialog(scheduleMeetingActivity, TimePickerDialog.THEME_HOLO_LIGHT, listener, hour,minute, false);
        t.show();
    }
}
