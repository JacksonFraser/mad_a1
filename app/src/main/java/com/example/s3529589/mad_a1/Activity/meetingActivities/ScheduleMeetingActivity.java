package com.example.s3529589.mad_a1.Activity.meetingActivities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.s3529589.mad_a1.Controller.meetingControllers.CreateMeetingController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.CustomDatePickerDialogController;
import com.example.s3529589.mad_a1.Model.CustomTimePickDialogController;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleMeetingActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_meeting);

        //Meeting title
        EditText meetingTitleView = (EditText) findViewById(R.id.meeting_title);
        String meetingTitle = meetingTitleView.getText().toString();


        //Meeting start date
        View startDateBtn = findViewById(R.id.start_date_btn);
        final EditText meetingStartDateET = (EditText) findViewById(R.id.start_time);
        String meetingStartDate = meetingStartDateET.getText().toString();
        startDateBtn.setOnClickListener(new CustomDatePickerDialogController(this,meetingStartDateET));

        //Meeting end date
        View endDateBtn = findViewById(R.id.end_date_btn);
        final EditText meetingEndDateET = (EditText) findViewById(R.id.end_time);
        String meetingEndDate = meetingEndDateET.getText().toString();
        endDateBtn.setOnClickListener(new CustomDatePickerDialogController(this,meetingEndDateET));

        //Meeting start time
        View startTimeBtn = findViewById(R.id.start_time_meeting_button);
        startTimeBtn.setOnClickListener(new CustomTimePickDialogController(this,meetingStartDateET));

        //Meeting end time
        View endTimeBtn = findViewById(R.id.end_time_meeting_button);
        endTimeBtn.setOnClickListener(new CustomTimePickDialogController(this,meetingEndDateET));

        final List<Friend> meetingFriendList = new ArrayList<>();
        View addFriendBtn = findViewById(R.id.add_friend_to_meeting_btn);
        addFriendBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String[] friends = new String[DataSingleton.getInstance().getFriendList().size()];
                final int[] friendsId = new int[DataSingleton.getInstance().getFriendList().size()];
                int i = 0;
                for(Friend f : DataSingleton.getInstance().getFriendList()){
                    friends[i] = f.getName();
                    friendsId[i] = f.getId();
                    i++;
                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleMeetingActivity.this);
                builder.setTitle("Select Friends");
                builder.setMultiChoiceItems(friends, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            for(Friend f : DataSingleton.getInstance().getFriendList()){
                                if(f.getId() == friendsId[which]){
                                    meetingFriendList.add(f);
                                }
                            }
                        }
                        if(!isChecked){
                            for(Friend f : DataSingleton.getInstance().getFriendList()) {
                                if (f.getId() == friendsId[which]) {
                                    meetingFriendList.remove(f);
                                }
                            }
                        }
                    }
                })
                .setPositiveButton("Comfirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            }
        });

        //Create meeting from data
        View createMeetingBtn = findViewById(R.id.create_meeting_confirm_btn);
        createMeetingBtn.setOnClickListener(new CreateMeetingController(this,meetingTitle,meetingStartDate,meetingEndDate,meetingFriendList));


    }
}

