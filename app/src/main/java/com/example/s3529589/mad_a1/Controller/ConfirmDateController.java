package com.example.s3529589.mad_a1.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.s3529589.mad_a1.Model.DatePickerActivity;
import com.example.s3529589.mad_a1.R;

/**
 * Created by s3529589 on 8/13/17.
 */

public class ConfirmDateController implements View.OnClickListener {
    private DatePickerActivity datePickerActivity;

    public ConfirmDateController(DatePickerActivity datePickerActivity) {
        this.datePickerActivity = datePickerActivity;
    }

    @Override
    public void onClick(View v) {
        DatePicker d = (DatePicker) datePickerActivity.findViewById(R.id.datePicker);
        datePickerActivity.finish();
    }

}
