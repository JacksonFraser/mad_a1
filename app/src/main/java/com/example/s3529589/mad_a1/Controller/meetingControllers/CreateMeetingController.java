package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.view.View;
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
    Date start;
    Date finish;

    private String meetingTitle;
    private String startTime;
    private String finishTime;
    private List<Friend> meetingFriendList;

    public CreateMeetingController(String meetingTitle, String start, String end, List<Friend> meetingFriendList) {
        this.meetingTitle = meetingTitle;
        this.startTime =  start;
        this.finishTime = end;
        this.meetingFriendList = meetingFriendList;
    }

    @Override
    public void onClick(View v) {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm aaa");
        String startInString = startTime;
        String finishInString = finishTime;

        try {
            start = formatter.parse(startInString);
            finish = formatter.parse(finishInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DataSingleton.getInstance().getMeetingList().add(new Meeting(meetingTitle, start, finish, meetingFriendList, "dummyLocation"));
        System.out.println(DataSingleton.getInstance().getMeetingById(0).getStartTime());
    }
}
