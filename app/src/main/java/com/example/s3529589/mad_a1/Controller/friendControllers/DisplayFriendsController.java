package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.content.Intent;
import android.view.View;

import com.example.s3529589.mad_a1.Activity.friendActivities.DisplayFriendActivity;
import com.example.s3529589.mad_a1.Activity.friendActivities.FriendMenuActivity;

public class DisplayFriendsController implements View.OnClickListener {
    private FriendMenuActivity friendMenuActivity;
    final int PICK_DISPLAY = 212;
    int id;

    public DisplayFriendsController(FriendMenuActivity friendMenuActivity) {
        this.friendMenuActivity = friendMenuActivity;
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(friendMenuActivity, DisplayFriendActivity.class);
        friendMenuActivity.startActivity(it);
        friendMenuActivity.finish();
    }

}
