package com.example.s3529589.mad_a1.Controller;

import android.Manifest;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.view.View;
<<<<<<< HEAD
import com.example.s3529589.mad_a1.Activity.FriendMenuActivity;
=======

import com.example.s3529589.mad_a1.Model.DatePickerActivity;
import com.example.s3529589.mad_a1.Model.FriendMenuActivity;
>>>>>>> testBranch


public class AddFriendController implements View.OnClickListener {
    private FriendMenuActivity friendMenuActivity;
    private static final int PICK_CONTACTS = 100;

    public AddFriendController(FriendMenuActivity friendMenuActivity) {
        this.friendMenuActivity = friendMenuActivity;
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(friendMenuActivity, DatePickerActivity.class);
        friendMenuActivity.startActivity(it);
    }


}
