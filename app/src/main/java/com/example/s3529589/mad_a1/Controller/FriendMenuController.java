package com.example.s3529589.mad_a1.Controller;

import android.Manifest;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.example.s3529589.mad_a1.Model.FriendMenuActivity;
import com.example.s3529589.mad_a1.Model.MainActivity;

public class FriendMenuController implements View.OnClickListener {
    private MainActivity mActivity;
    protected static final int PICK_CONTACTS = 100;

    public FriendMenuController(MainActivity mainActivity) {
        this.mActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        // Request for permissions to read contacts

        Intent it = new Intent(mActivity, FriendMenuActivity.class);
        mActivity.startActivity(it);
    }

  /*  private void check() {
        ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.READ_CONTACTS}, 1);
    }
    */
}
