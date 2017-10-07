package com.example.s3529589.mad_a1.Activity.friendActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.friendControllers.AddFriendController;
import com.example.s3529589.mad_a1.Controller.friendControllers.DisplayFriendsController;
import com.example.s3529589.mad_a1.R;
import com.example.s3529589.mad_a1.Services.ApplicationTrackerSingleton;

public class FriendMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_menu);
        ApplicationTrackerSingleton.getInstance().setCurrentActivity(this);
        View addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new AddFriendController(this));

        View displayBtn = findViewById(R.id.displayFriendsBtn);
        displayBtn.setOnClickListener(new DisplayFriendsController(this));
    }
}

