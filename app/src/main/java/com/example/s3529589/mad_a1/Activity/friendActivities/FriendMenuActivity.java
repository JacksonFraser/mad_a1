package com.example.s3529589.mad_a1.Activity.friendActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.s3529589.mad_a1.Controller.friendControllers.AddFriendController;
import com.example.s3529589.mad_a1.Controller.friendControllers.DisplayFriendsController;
import com.example.s3529589.mad_a1.R;

public class FriendMenuActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_menu);

        View addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new AddFriendController(this));

        View displayBtn = findViewById(R.id.displayFriendBtn);
        displayBtn.setOnClickListener(new DisplayFriendsController(this));
    }
}

