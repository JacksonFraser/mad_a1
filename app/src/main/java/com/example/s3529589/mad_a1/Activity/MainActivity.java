package com.example.s3529589.mad_a1.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.friendControllers.FriendMenuController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingMenuController;
import com.example.s3529589.mad_a1.Exceptions.InvalidMeetingInput;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.Meeting;
import com.example.s3529589.mad_a1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        addDummyData();
        View friendMenuBtn = findViewById(R.id.friendMenuBtn);
        friendMenuBtn.setOnClickListener(new FriendMenuController(this));

        View meetingMenuBtn = findViewById(R.id.meetingMenuBtn);
        meetingMenuBtn.setOnClickListener(new MeetingMenuController(this));
    }

    private void addDummyData(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Date date1 = calendar.getTime();
        Date date2 = calendar.getTime();
        Friend f1 = new Friend("BOBBY FLAG", "gmail@gmail", date);
        Friend f2 = new Friend("GOOD PERSON", "newemail@me.com", date);
        Friend f3 = new Friend("Sally Sanders", "sally@me.com", date);
        Friend f4 = new Friend("dennis Le Mennis", "dennis@me.com", date);
        DataSingleton.getInstance().getFriendList().add(f1);
        DataSingleton.getInstance().getFriendList().add(f2);
        DataSingleton.getInstance().getFriendList().add(f3);
        DataSingleton.getInstance().getFriendList().add(f4);
        try{
            List<Friend> friendList1 = new ArrayList<>();

            friendList1.add(f1);
            friendList1.add(f2);
            friendList1.add(f3);
            friendList1.add(f4);

            List<Friend> friendList2 = new ArrayList<>();



            Meeting m1 = new Meeting("meeting 1",date1,date2,friendList1,"+330.1131, +11.11");
            Meeting m2 = new Meeting("meeting 2",date1,date2,friendList2,"+331.131, +111");
            DataSingleton.getInstance().getMeetingList().add(m1);
            DataSingleton.getInstance().getMeetingList().add(m2);

        }catch (InvalidMeetingInput e){
            System.out.println(e.getMessage());
        }

    }
}






