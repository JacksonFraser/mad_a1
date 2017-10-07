package com.example.s3529589.mad_a1.Activity;

import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.friendControllers.FriendMenuController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingMenuController;
import com.example.s3529589.mad_a1.Database.DBHelper;
import com.example.s3529589.mad_a1.Database.DatabaseManagerSingleton;
import com.example.s3529589.mad_a1.Database.FriendTable;
import com.example.s3529589.mad_a1.Database.MeetingTable;
import com.example.s3529589.mad_a1.Exceptions.InvalidMeetingInput;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.Meeting;
import com.example.s3529589.mad_a1.R;
import com.example.s3529589.mad_a1.Services.MeetingJobService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        createMeetingJobScheduler();
        DBHelper dbHelper = new DBHelper(this);
        DatabaseManagerSingleton.initialise(dbHelper);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        View friendMenuBtn = findViewById(R.id.friendMenuBtn);
        friendMenuBtn.setOnClickListener(new FriendMenuController(this));

        View meetingMenuBtn = findViewById(R.id.meetingMenuBtn);

        meetingMenuBtn.setOnClickListener(new MeetingMenuController(this));

        // Uncomment these to restore dummy data
        this.deleteDatabase("mad_db");
        addDummyDatabaseStuff();

        //Only runs if you don't have contact permission
        checkPersissions();
    }

    private void checkPersissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS}, 0);
            }
        }
    }

    private void addDummyDatabaseStuff(){
        FriendTable friendTable = new FriendTable();
        MeetingTable meetingTable = new MeetingTable();
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();
        Date date2 = calendar.getTime();

        Friend f1 = new Friend("Bobby Jarzombek", "gmail@gmail", date1, -37.824620, 144.957029);
        Friend f2 = new Friend("Chris Dave", "newemail@me.com", date1, -37.782931, 144.911485);
        Friend f3 = new Friend("Sally Sanders", "sally@me.com", date1, -37.817000, 144.946000);
        Friend f4 = new Friend("dennis Le Mennis", "dennis@me.com", date1, -37.807580, 144.956785);
        friendTable.addFriend(f1);
        friendTable.addFriend(f2);
        friendTable.addFriend(f3);
        friendTable.addFriend(f4);


        try {
            List<Friend> friendList1 = new ArrayList<>();

            friendList1.add(f1);
            friendList1.add(f2);
            friendList1.add(f3);
            friendList1.add(f4);

            List<Friend> friendList2 = new ArrayList<>();

            Meeting m1 = new Meeting("meeting 1", date1, date2, "-37.867679, 144.976866");
            Meeting m2 = new Meeting("meeting 2", date1, date2, "37.421814, -122.084364");
            meetingTable.addMeeting(m1);
            meetingTable.addMeeting(m2);

        } catch (InvalidMeetingInput invalidMeetingInput) {
            invalidMeetingInput.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createMeetingJobScheduler(){
        ComponentName componentName = new ComponentName(this, MeetingJobService.class);

        // try this
        startService(new Intent(getBaseContext(), DistanceMatrixAPIService.class));

        JobInfo jobInfo = new JobInfo.Builder(12, componentName)
                .setRequiresCharging(true)
                .setPeriodic(3000)
                .build();

        JobScheduler jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(jobInfo);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            System.out.println("kljasdhkj");
        } else {
            System.out.println("not kljasdhkj");
        }

    }
}






