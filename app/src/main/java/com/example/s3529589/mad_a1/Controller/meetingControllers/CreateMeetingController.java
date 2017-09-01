package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.s3529589.mad_a1.Activity.meetingActivities.ScheduleMeetingActivity;
import com.example.s3529589.mad_a1.Exceptions.InvalidDateInput;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.Meeting;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class CreateMeetingController implements View.OnClickListener {

    private ScheduleMeetingActivity scheduleMeetingActivity;

    private EditText meetingTitle;
    private TextView startTime;
    private TextView finishTime;
    private List<Friend> meetingFriendList;

    public CreateMeetingController(EditText meetingTitle, TextView startTime, TextView finishTime, List<Friend> meetingFriendList) {
        this.meetingTitle = meetingTitle;
        this.startTime =  startTime;
        this.finishTime = finishTime;
        this.meetingFriendList = meetingFriendList;
    }

    @Override
    public void onClick(View v) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm aaa");
        String startInString = startTime.getText().toString();
        String finishInString = finishTime.getText().toString();

        try {
            Date start = formatter.parse(startInString);
            Date finish = formatter.parse(finishInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DataSingleton.getInstance().getMeetingList().add(new Meeting(meetingTitle.getText().toString(), start, finish, meetingFriendList, "11"));

        System.out.println(meetingTitle.getText().toString());
        System.out.println(start);
        System.out.println(finish);

    }
}
