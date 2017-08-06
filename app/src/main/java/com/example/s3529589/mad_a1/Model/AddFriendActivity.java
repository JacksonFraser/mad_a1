package com.example.s3529589.mad_a1.Model;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

/**
 * Created by s3530377 on 8/6/17.
 */

public class AddFriendActivity extends Activity {

    protected static final int PICK_CONTACTS = 100;
    private String LOG_TAG = this.getClass().getName();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACTS) {
            if (resultCode == RESULT_OK) {
                ContactDataManager contactsManager = new ContactDataManager(this, data);
                String name = "";
                String email = "";
                try {
                    name = contactsManager.getContactName();
                    email = contactsManager.getContactEmail();
                } catch (ContactDataManager.ContactQueryException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
            }
        }
    }
}
