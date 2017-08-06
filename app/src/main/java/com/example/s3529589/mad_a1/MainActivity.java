package com.example.s3529589.mad_a1;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    protected static final int PICK_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View addFriendBtn = findViewById(R.id.addFriends);

        addFriendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addFriendActivity(v);
            }
        });
    }


    public void addFriendActivity(View view){
        this.setContentView(R.layout.add_friend);

        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, PICK_CONTACTS);
    }
}






