package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.view.View;

import com.example.s3529589.mad_a1.Activity.friendActivities.SelectContactActivity;
import com.example.s3529589.mad_a1.Activity.friendActivities.FriendMenuActivity;
import com.example.s3529589.mad_a1.Model.WorkerRunnable;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class AddFriendController implements View.OnClickListener {
    private FriendMenuActivity friendMenuActivity;
    private Thread createFriendThread;
    private Queue<Thread> threadQueue = new LinkedBlockingQueue<>();

    public AddFriendController(FriendMenuActivity friendMenuActivity) {
        this.friendMenuActivity = friendMenuActivity;
    }

    @Override
    public void onClick(View v) {

        createFriendThread = new Thread(new WorkerRunnable(friendMenuActivity));
        threadQueue.add(createFriendThread);
        createFriendThread.setDaemon(true);
        createFriendThread.start();

    }
}
