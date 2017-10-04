package com.example.s3529589.mad_a1.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.example.s3529589.mad_a1.Controller.friendControllers.FriendMenuController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingMenuController;
import com.example.s3529589.mad_a1.Database.DBHelper;
import com.example.s3529589.mad_a1.Database.DatabaseManagerSingleton;
import com.example.s3529589.mad_a1.Database.FriendTable;
import com.example.s3529589.mad_a1.Database.MeetingDatabaseHandler;
import com.example.s3529589.mad_a1.Database.FriendDatabaseHandler;
import com.example.s3529589.mad_a1.Database.MeetingTable;
import com.example.s3529589.mad_a1.Exceptions.InvalidMeetingInput;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.Meeting;
import com.example.s3529589.mad_a1.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

    }

    private void addDummyData() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Date date1 = calendar.getTime();
        Date date2 = calendar.getTime();
        Friend f1 = new Friend("Bobby Jarzombek", "gmail@gmail", date, 0, 0);
        Friend f2 = new Friend("Chris Dave", "newemail@me.com", date, 0, 0);
        Friend f3 = new Friend("Sally Sanders", "sally@me.com", date, 0, 0);
        Friend f4 = new Friend("dennis Le Mennis", "dennis@me.com", date, 0, 0);
        DataSingleton.getInstance().getFriendList().add(f1);
        DataSingleton.getInstance().getFriendList().add(f2);
        DataSingleton.getInstance().getFriendList().add(f3);
        DataSingleton.getInstance().getFriendList().add(f4);
        try {
            List<Friend> friendList1 = new ArrayList<>();

            friendList1.add(f1);
            friendList1.add(f2);
            friendList1.add(f3);
            friendList1.add(f4);

            List<Friend> friendList2 = new ArrayList<>();

            Meeting m1 = new Meeting("meeting 1", date1, date2, "+330.1131, +11.11");
            Meeting m2 = new Meeting("meeting 2", date1, date2, "+331.131, +111");
            DataSingleton.getInstance().getMeetingList().add(m1);
            DataSingleton.getInstance().getMeetingList().add(m2);

        } catch (InvalidMeetingInput e) {
            System.out.println(e.getMessage());
        }

    }

    private void addDummyDatabaseStuff(){
        FriendTable friendTable = new FriendTable();
        MeetingTable meetingTable = new MeetingTable();
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();
        Date date2 = calendar.getTime();

        Friend f1 = new Friend("Bobby Jarzombek", "gmail@gmail", date1, -37.824620, 144.957029);
        Friend f2 = new Friend("Chris Dave", "newemail@me.com", date1, 0, 0);
        Friend f3 = new Friend("Sally Sanders", "sally@me.com", date1, 0, 0);
        Friend f4 = new Friend("dennis Le Mennis", "dennis@me.com", date1, 0, 0);
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

            Meeting m1 = new Meeting("meeting 1", date1, date2, "+330.1131, +11.11");
            Meeting m2 = new Meeting("meeting 2", date1, date2, "+331.131, +111");
            meetingTable.addMeeting(m1);
            meetingTable.addMeeting(m2);

         //   for(Meeting m : mdb.getAllMeetings()){
          //      System.out.println(m.getStartTime());
           // }
        } catch (InvalidMeetingInput invalidMeetingInput) {
            invalidMeetingInput.printStackTrace();
        }
    }

}






