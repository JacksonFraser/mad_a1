package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.content.Intent;
import android.view.View;

import com.example.s3529589.mad_a1.Activity.friendActivities.FriendMenuActivity;
import com.example.s3529589.mad_a1.Activity.MainActivity;

public class FriendMenuController implements View.OnClickListener {
    private MainActivity mainActivity;

    public FriendMenuController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(mainActivity, FriendMenuActivity.class);
        mainActivity.startActivity(it);
    }
}
