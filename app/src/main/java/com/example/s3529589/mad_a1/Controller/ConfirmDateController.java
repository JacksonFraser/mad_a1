package com.example.s3529589.mad_a1.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.s3529589.mad_a1.Model.DatePickerActivity;
import com.example.s3529589.mad_a1.Model.FriendMenuActivity;
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
        String birthday =
                 String.valueOf(d.getDayOfMonth())
                +String.valueOf(d.getMonth())
                +String.valueOf(d.getYear());

        Bundle b = new Bundle();
        b.putString("name", datePickerActivity.getName());
        b.putString("email", datePickerActivity.getEmail());
        b.putString("birthday", birthday);
        Intent it = new Intent(datePickerActivity, FriendMenuActivity.class);
        it.putExtra("friendDetails",b);
         FriendMenuActivity.startActivityForResult(it, 10);
    }

}
