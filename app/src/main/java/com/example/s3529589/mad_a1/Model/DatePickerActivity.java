package com.example.s3529589.mad_a1.Model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import android.widget.DatePicker;

import com.example.s3529589.mad_a1.Controller.ConfirmDateController;
import com.example.s3529589.mad_a1.R;

/**
 * Created by s3529589 on 8/13/17.
 */

public class DatePickerActivity extends Activity {

    private DatePicker birthday;
    private final int PICK_CONTACTS = 100;
    private String name;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);

        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        this.startActivityForResult(contactPickerIntent, PICK_CONTACTS);

        View confirmBtn = findViewById(R.id.confirmDateBtn);
        confirmBtn.setOnClickListener(new ConfirmDateController(this));

    }


    public String getName(){

        return name;

    }

    public String getEmail(){

        return email;

    }

    public DatePicker getDatePicker(){

        return birthday;

    }
    public void setDatePicker(DatePicker d){
        this.birthday = d;
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
