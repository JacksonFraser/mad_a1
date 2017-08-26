package com.example.s3529589.mad_a1.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.friendControllers.FriendMenuController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingMenuController;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        adddummydata();
        View friendMenuBtn = findViewById(R.id.friendMenuBtn);
        friendMenuBtn.setOnClickListener(new FriendMenuController(this));

        View meetingMenuBtn = findViewById(R.id.meetingMenuBtn);
        meetingMenuBtn.setOnClickListener(new MeetingMenuController(this));
    }
    private void adddummydata(){
        Friend f1 = new Friend("BOBBY FLAG", "KHAKDJAHD", "10101");
        Friend f2 = new Friend("GOOD PERSON", "aaaaaaaaaa", "99999");
        DataSingleton.getInstance().getFriendList().add(f1);
        DataSingleton.getInstance().getFriendList().add(f2);
    }
}






