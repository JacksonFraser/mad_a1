package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.app.TimePickerDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.s3529589.mad_a1.Adapter.MeetingArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class MeetingTimeEditController implements View.OnClickListener {
    private UUID id;
    private MeetingArrayAdapter meetingArrayAdapter;
    private TextView startTimeTV;

    public MeetingTimeEditController(UUID id, MeetingArrayAdapter meetingArrayAdapter, TextView startTimeTV) {
        this.id = id;
        this.meetingArrayAdapter = meetingArrayAdapter;
        this.startTimeTV = startTimeTV;
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
                Date startTime = cal.getTime();

                SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy, h:mm:ss a");
                String labelTime = formatter.format(startTime);
                startTimeTV.setText(labelTime);
            }
        };
        TimePickerDialog t = new TimePickerDialog(meetingArrayAdapter.getContext(), TimePickerDialog.THEME_HOLO_LIGHT, listener, hour, minute, false);
        t.show();
    }
}
