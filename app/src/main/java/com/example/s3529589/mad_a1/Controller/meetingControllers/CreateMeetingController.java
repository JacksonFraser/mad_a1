package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.view.View;

import com.example.s3529589.mad_a1.Activity.meetingActivities.ScheduleMeetingActivity;
import com.example.s3529589.mad_a1.Model.Friend;

import java.util.List;

/**
 * Created by s3529589 on 8/30/17.
 */

public class CreateMeetingController implements View.OnClickListener {
    private ScheduleMeetingActivity scheduleMeetingActivity;
    private String meetingTitle;
    private String meetingStartTime;
    private String meetingEndTime;
    private List<Friend> meetingFriendList;

    public CreateMeetingController(ScheduleMeetingActivity scheduleMeetingActivity, String meetingTitle, String meetingStartDate, String meetingEndDate, List<Friend> meetingFriendList) {
        this.scheduleMeetingActivity = scheduleMeetingActivity;
        this.meetingTitle = meetingTitle;
        this.meetingStartTime =  meetingStartDate;
        this.meetingEndTime = meetingEndDate;
        this.meetingFriendList = meetingFriendList;

    }

    @Override
    public void onClick(View v) {
        System.out.println(meetingFriendList.get(0).getBirthdate());
        System.out.println(meetingFriendList.get(1).getBirthdate());
    }
}
