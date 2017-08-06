package com.example.s3529589.mad_a1;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;

/**
 * Created by s3529589 on 8/6/17.
 */

public class addFriendController implements View.OnClickListener {
    private MainActivity mActivity;

    public addFriendController(MainActivity mainActivity) {
        this.mActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        mActivity.startActivityForResult(contactPickerIntent, 100);



    }
}
