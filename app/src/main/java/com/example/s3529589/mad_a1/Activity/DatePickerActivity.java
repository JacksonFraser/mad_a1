package com.example.s3529589.mad_a1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.s3529589.mad_a1.Controller.ConfirmDateController;
import com.example.s3529589.mad_a1.R;

public class DatePickerActivity extends AppCompatActivity {
    private Button confirmBtn;
    private DatePicker datePicker;
    private String name;
    private String email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);

        confirmBtn = (Button) findViewById(R.id.confirmDateBtn);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        // receive intents from PickContactActivity
        Intent incomingIntent = getIntent();
        name = incomingIntent.getStringExtra("name");
        email = incomingIntent.getStringExtra("email");

        confirmBtn.setOnClickListener(new ConfirmDateController(name, email, datePicker, this));
    }

    @Override
    public void onBackPressed()
    {
        Intent it = new Intent(DatePickerActivity.this, FriendMenuActivity.class);
        startActivity(it);

        //finish DatePickerActivity
        finish();
    }
}

