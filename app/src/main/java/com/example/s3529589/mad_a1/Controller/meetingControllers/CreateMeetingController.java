package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.s3529589.mad_a1.Activity.meetingActivities.ScheduleMeetingActivity;
import com.example.s3529589.mad_a1.Exceptions.InvalidMeetingInput;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.Meeting;
import com.example.s3529589.mad_a1.R;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CreateMeetingController implements View.OnClickListener {
    private ScheduleMeetingActivity scheduleMeetingActivity;

    private EditText meetingTitle;
    private TextView startTime;
    private TextView finishTime;
    private List<Friend> meetingFriendList;
    private EditText meetingLocation;

    public CreateMeetingController(ScheduleMeetingActivity scheduleMeetingActivity, EditText meetingTitle, TextView startTime, TextView finishTime, List<Friend> meetingFriendList, EditText meetingLocation) {
        this.scheduleMeetingActivity = scheduleMeetingActivity;
        this.meetingTitle = meetingTitle;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.meetingFriendList = meetingFriendList;
        this.meetingLocation = meetingLocation;
    }

    @Override
    public void onClick(View v) {
        String startInString = startTime.getText().toString();
        String finishInString = finishTime.getText().toString();

        // Convert String to Date
        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy, h:mm:ss a");
        Date start = null;
        Date finish = null;

        try {
            start = formatter.parse(startInString);
            finish = formatter.parse(finishInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            DataSingleton.getInstance().getMeetingList().add(new Meeting(meetingTitle.getText().toString(), start, finish, meetingFriendList, meetingLocation.getText().toString()));
            scheduleMeetingActivity.finish();
            Toast.makeText(this.scheduleMeetingActivity, R.string.meeting_created_toast, Toast.LENGTH_SHORT).show();

        } catch (InvalidMeetingInput e) {
            Toast.makeText(this.scheduleMeetingActivity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
