package com.example.s3529589.mad_a1.Activity.friendActivities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

import com.example.s3529589.mad_a1.Model.ContactDataManager;
import com.example.s3529589.mad_a1.R;

public class SelectContactActivity extends AppCompatActivity {
    private String name;
    private String email;
    private int PICK_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_menu);

        // check();
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, PICK_CONTACTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                ContactDataManager contactsManager = new ContactDataManager(this, data);
                try {
                    this.name = contactsManager.getContactName();
                    this.email = contactsManager.getContactEmail();

                    // send data to CreateFriendActivity
                    Intent it = new Intent(SelectContactActivity.this, CreateFriendActivity.class);
                    it.putExtra("name", this.name);
                    it.putExtra("email", this.email);

                    // start CreateFriendActivity
                    startActivity(it);

                    // finish PickContactActivity
                    finish();

                } catch (ContactDataManager.ContactQueryException e) {
                }
            } else {
                Intent it = new Intent(SelectContactActivity.this, FriendMenuActivity.class);
                startActivity(it);
                // finish PickContactActivity
                finish();
            }
        }
    }
}
