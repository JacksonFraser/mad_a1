package com.example.s3529589.mad_a1.Controller;

import android.view.View;
import android.widget.DatePicker;
import com.example.s3529589.mad_a1.Activity.DatePickerActivity;
import com.example.s3529589.mad_a1.R;

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
