package com.example.s3529589.mad_a1.Model;

import android.content.Intent;
import android.os.Handler;

import com.example.s3529589.mad_a1.Activity.friendActivities.CreateFriendActivity;
import com.example.s3529589.mad_a1.Activity.friendActivities.FriendMenuActivity;

/**
 * Created by s3529589 on 9/13/17.
 */

public class WorkerRunnable implements Runnable {
    private FriendMenuActivity friendMenuActivity;
    public WorkerRunnable(FriendMenuActivity friendMenuActivity) {
        this.friendMenuActivity = friendMenuActivity;
    }

    @Override
    public void run() {
        friendMenuActivity.runOnUiThread(this);
        friendMenuActivity.getHandler().post(this);
        System.out.println("STARTED RUNNING");
        Intent it = new Intent(friendMenuActivity, CreateFriendActivity.class);
        friendMenuActivity.startActivity(it);
        friendMenuActivity.finish();
    }
}
