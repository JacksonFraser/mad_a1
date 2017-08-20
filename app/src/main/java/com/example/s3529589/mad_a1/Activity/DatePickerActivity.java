package com.example.s3529589.mad_a1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

public class DatePickerActivity extends AppCompatActivity {
    private Button confirmBtn;
    private DatePicker mDatePicker;
    private String name;
    private String email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);

        confirmBtn = (Button) findViewById(R.id.confirmDateBtn);
        mDatePicker = (DatePicker) findViewById(R.id.datePicker);

        // receive intents from PickContactActivity
        Intent incomingIntent = getIntent();
        name = incomingIntent.getStringExtra("name");
        email = incomingIntent.getStringExtra("email");


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String birthday = mDatePicker.getDayOfMonth()+"/"+mDatePicker.getMonth();

                // add to the Friends ArrayList
                DataSingleton d = DataSingleton.getInstance();
                d.getFriendList().add(new Friend(name, email, birthday));

                Intent it = new Intent(DatePickerActivity.this, FriendMenuActivity.class);
                startActivity(it);

                //finish DatePickerActivity
                finish();
            }
        });
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

