package com.example.s3529589.mad_a1.Model;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.s3529589.mad_a1.Controller.DatePickerController;
import com.example.s3529589.mad_a1.R;

/**
 * Created by s3529589 on 8/13/17.
 */

public class DatePickerActivity extends Activity {

    private DatePicker birthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        View confirmBtn = findViewById(R.id.confirmDateBtn);
        confirmBtn.setOnClickListener(new DatePickerController(this));

    }

    public DatePicker getDatePicker(){

        return birthday;

    }
    public void setDatePicker(DatePicker d){
        this.birthday = d;
    }
}
