package com.example.s3529589.mad_a1.Controller;

import android.Manifest;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import com.example.s3529589.mad_a1.Activity.FriendMenuActivity;


public class AddFriendController implements View.OnClickListener {
    private FriendMenuActivity friendMenuActivity;
    private static final int PICK_CONTACTS = 100;

    public AddFriendController(FriendMenuActivity friendMenuActivity) {
        this.friendMenuActivity = friendMenuActivity;
    }

    @Override
    public void onClick(View v) {
        check();
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        friendMenuActivity.startActivityForResult(contactPickerIntent, PICK_CONTACTS);
    }

    private void check() {
        ActivityCompat.requestPermissions(friendMenuActivity, new String[]{Manifest.permission.READ_CONTACTS}, 1);
    }
}
