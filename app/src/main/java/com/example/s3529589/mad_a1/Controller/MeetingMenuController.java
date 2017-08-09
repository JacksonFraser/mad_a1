package com.example.s3529589.mad_a1.Controller;

import android.content.Intent;
import android.view.View;

import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.FriendListActivity;
import com.example.s3529589.mad_a1.Model.MainActivity;
import com.example.s3529589.mad_a1.Model.MeetingMenuActivity;
import com.example.s3529589.mad_a1.R;

public class MeetingMenuController implements View.OnClickListener {
    private MainActivity mainActivity;

    public MeetingMenuController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(mainActivity, MeetingMenuActivity.class);
        mainActivity.startActivity(it);
    }
}
