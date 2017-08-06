package com.example.s3529589.mad_a1.Controller;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;

import com.example.s3529589.mad_a1.Model.MainActivity;

/**
 * Created by s3529589 on 8/6/17.
 */

public class AddFriendController implements View.OnClickListener {
    private MainActivity mActivity;
    protected static final int PICK_CONTACTS = 100;

    public AddFriendController(MainActivity mainActivity) {
        this.mActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        mActivity.startActivityForResult(contactPickerIntent, PICK_CONTACTS);
    }
}
