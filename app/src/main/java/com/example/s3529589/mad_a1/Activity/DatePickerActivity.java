package com.example.s3529589.mad_a1.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.s3529589.mad_a1.Model.ContactDataManager;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

public class DatePickerActivity extends AppCompatActivity {

    private Button confirmBtn;
    private DatePicker mDatePicker;
    private String name;
    private String email;
    private int PICK_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);


        // check();
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, PICK_CONTACTS);

        confirmBtn = (Button) findViewById(R.id.confirmDateBtn);
        mDatePicker = (DatePicker) findViewById(R.id.datePicker);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String birthdate = mDatePicker.getDayOfMonth()+"/"+mDatePicker.getMonth();

                // Return back to FriendMenuActivity
                Intent intent = new Intent(DatePickerActivity.this, FriendMenuActivity.class);
                DataSingleton d = DataSingleton.getInstance();
                d.getFriendList().add(new Friend(name,email,birthdate));
                startActivity(intent);

                // Finish DatePickerActivity
                finish();
            }
        });

    }

    private void check() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                ContactDataManager contactsManager = new ContactDataManager(this, data);
                String name = "";
                String email = "";
                try {
                    this.name = contactsManager.getContactName();
                    this.email = contactsManager.getContactEmail();


                } catch (ContactDataManager.ContactQueryException e) {
                }
            }
        }
    }




}

