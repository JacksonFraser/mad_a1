package com.example.s3529589.mad_a1.Activity.meetingActivities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.s3529589.mad_a1.Controller.meetingControllers.CreateMeetingController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.FinishMeetingTimeController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.StartMeetingTimeController;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_meeting);

        //Meeting title
        EditText meetingTitleView = (EditText) findViewById(R.id.meetingTitle);

        //Meeting start time
        View startTimeBtn = findViewById(R.id.startTimeBtn);
        TextView startTime = (TextView) findViewById(R.id.startTimeLbl);
        startTimeBtn.setOnClickListener(new StartMeetingTimeController(this, startTime));

        //Meeting end time
        View endTimeBtn = findViewById(R.id.finishTimeBtn);
        TextView finishTime = (TextView) findViewById(R.id.finishTimeLbl);
        endTimeBtn.setOnClickListener(new FinishMeetingTimeController(this, finishTime));

        final List<Friend> meetingFriendList = new ArrayList<>();
        View pickFriends = findViewById(R.id.selectFriendsBtn);
        pickFriends.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String[] friends = new String[DataSingleton.getInstance().getFriendList().size()];
                final int[] friendsId = new int[DataSingleton.getInstance().getFriendList().size()];
                int i = 0;
                for(Friend f : DataSingleton.getInstance().getFriendList()){
                    try{
                        friends[i] = f.getName();
                        friendsId[i] = f.getId();
                        i++;

                    }catch (Exception e){

                    }
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

        //Create a meeting
        View createMeetingBtn = findViewById(R.id.confirmMeetingBtn);
        createMeetingBtn.setVisibility(View.VISIBLE);
        createMeetingBtn.setOnClickListener(new CreateMeetingController(this,meetingTitleView, startTime, finishTime, meetingFriendList));
    }

}

