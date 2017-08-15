package com.example.s3529589.mad_a1.Model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import com.example.s3529589.mad_a1.R;

/**
 * Created by s3529589 on 8/13/17.
 */

public class DatePickerActivity extends AppCompatActivity {

    private Button confirmBtn;
    private DatePicker mDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);

        confirmBtn = (Button) findViewById(R.id.confirmDateBtn);
        mDatePicker = (DatePicker) findViewById(R.id.datePicker);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = mDatePicker.getDayOfMonth()+"/"+mDatePicker.getMonth();
                System.out.println("Printint date in DatePickerActivity: " + date);

                // Return back to FriendMenuActivity
                Intent intent = new Intent(DatePickerActivity.this, FriendMenuActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }


}

