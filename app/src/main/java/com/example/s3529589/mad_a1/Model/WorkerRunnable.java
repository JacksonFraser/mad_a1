package com.example.s3529589.mad_a1.Model;

import android.content.Intent;
import android.os.Handler;

import com.example.s3529589.mad_a1.Activity.friendActivities.CreateFriendActivity;
import com.example.s3529589.mad_a1.Activity.friendActivities.FriendMenuActivity;
import com.example.s3529589.mad_a1.Activity.friendActivities.SelectContactActivity;

/**
 * Created by s3529589 on 9/13/17.
 */

public class WorkerRunnable implements Runnable {
    private FriendMenuActivity friendMenuActivity;
    private Handler uiHandler;
    public WorkerRunnable(FriendMenuActivity friendMenuActivity) {
        this.friendMenuActivity = friendMenuActivity;
        this.uiHandler = friendMenuActivity.getHandler();
    }

    @Override
    public void run() {
        System.out.println("HELLLLOOOOOO");
        uiHandler.post(this);
        Intent it = new Intent(friendMenuActivity, SelectContactActivity.class);
        friendMenuActivity.startActivity(it);
        friendMenuActivity.runOnUiThread(this);
        friendMenuActivity.finish();
    }
}
