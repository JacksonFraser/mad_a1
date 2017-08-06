package com.example.s3529589.mad_a1.Controller;

import android.Manifest;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.example.s3529589.mad_a1.Model.MainActivity;

public class AddFriendController implements View.OnClickListener {
    private MainActivity mActivity;
    protected static final int PICK_CONTACTS = 100;

    public AddFriendController(MainActivity mainActivity) {
        this.mActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        // Request for permissions to read contacts
        check();

        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        mActivity.startActivityForResult(contactPickerIntent, PICK_CONTACTS);
    }

    private void check() {
        ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.READ_CONTACTS}, 1);
    }
}
