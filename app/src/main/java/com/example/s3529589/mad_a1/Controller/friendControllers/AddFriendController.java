package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.content.Intent;
import android.view.View;

import com.example.s3529589.mad_a1.Activity.CreateFriendActivity;
import com.example.s3529589.mad_a1.Activity.FriendMenuActivity;

public class AddFriendController implements View.OnClickListener {
    private FriendMenuActivity friendMenuActivity;

    public AddFriendController(FriendMenuActivity friendMenuActivity) {
        this.friendMenuActivity = friendMenuActivity;
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(friendMenuActivity, CreateFriendActivity.class);
        friendMenuActivity.startActivity(it);
        friendMenuActivity.finish();
    }
}
