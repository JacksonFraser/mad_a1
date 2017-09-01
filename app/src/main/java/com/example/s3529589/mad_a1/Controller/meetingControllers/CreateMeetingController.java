package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.view.View;
import android.widget.TextView;

import com.example.s3529589.mad_a1.Activity.meetingActivities.ScheduleMeetingActivity;
import com.example.s3529589.mad_a1.Exceptions.InvalidDateInput;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.Meeting;

import java.util.Date;
import java.util.List;

public class CreateMeetingController implements View.OnClickListener {
    private String meetingTitle;
    private Date startTime;
    private Date finishTime;
    private List<Friend> meetingFriendList;

    public CreateMeetingController(String meetingTitle, Date start, Date end, List<Friend> meetingFriendList) {
        this.meetingTitle = meetingTitle;
        this.startTime =  start;
        this.finishTime = end;
        this.meetingFriendList = meetingFriendList;
    }

    @Override
    public void onClick(View v) {
        DataSingleton.getInstance().getMeetingList().add(new Meeting(meetingTitle, startTime, finishTime, meetingFriendList, "dummyLocation"));
        System.out.println(DataSingleton.getInstance().getMeetingById(0).getStartTime());
    }
}
