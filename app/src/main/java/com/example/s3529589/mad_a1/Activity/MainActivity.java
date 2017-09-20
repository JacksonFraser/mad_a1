package com.example.s3529589.mad_a1.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.example.s3529589.mad_a1.Controller.friendControllers.FriendMenuController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingMenuController;
import com.example.s3529589.mad_a1.Database.FriendDatabaseHandler;
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);



        FriendDatabaseHandler db = new FriendDatabaseHandler(this);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        db.addFriend(new Friend("Bobby Jarzombek", "gmail@gmail", date, 0, 0));
        db.addFriend(new Friend("Chris Dave", "newemail@me.com", date, 0, 0));
        db.addFriend(new Friend("Sally Sanders", "sally@me.com", date, 0, 0));

        // Reading all contacts
        List<Friend> friends = db.getAllFriends();

        for (Friend f : friends) {
            String log = "Id: "+f.getId()+", Name: " + f.getName() + ", Email: " + f.getEmail() + ", Date: " + f.getBirthdate() + ", Longitude: " +
            f.getLon() + ", Latitude: " +  f.getLat() + " " + friends.size();
            // Writing Contacts to log
            Log.d("Entry number: ", log);
            db.deleteFriend(f);
        }

        addDummyData();
        View friendMenuBtn = findViewById(R.id.friendMenuBtn);
        friendMenuBtn.setOnClickListener(new FriendMenuController(this));



        View meetingMenuBtn = findViewById(R.id.meetingMenuBtn);
        meetingMenuBtn.setOnClickListener(new MeetingMenuController(this));
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


            Meeting m1 = new Meeting("meeting 1", date1, date2, friendList1, "+330.1131, +11.11");
            Meeting m2 = new Meeting("meeting 2", date1, date2, friendList2, "+331.131, +111");
            DataSingleton.getInstance().getMeetingList().add(m1);
            DataSingleton.getInstance().getMeetingList().add(m2);

        } catch (InvalidMeetingInput e) {
            System.out.println(e.getMessage());
        }

    }

}






