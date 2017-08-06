package com.example.s3529589.mad_a1.Controller;

import android.view.View;

import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.MainActivity;

/**
 * Created by s3529589 on 8/6/17.
 */

public class DisplayFriendListController implements View.OnClickListener {
    private MainActivity mainActivity;

    public DisplayFriendListController(MainActivity mainActivity) {
        System.out.println("DISPLAY GOT CREATED");
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        System.out.println("LIST GOT DISPLAYED");
        for(Friend f : mainActivity.getFriendList()){
            System.out.println(f.getName());
        }
    }
}
