package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import com.example.s3529589.mad_a1.Model.DataSingleton;

import java.util.Calendar;

public class EditBirthDateController implements View.OnClickListener {
    private Context context;
    private int id;

    public EditBirthDateController(Context context, int id) {
        this.context = context;
        this.id = id;
    }

    @Override
    public void onClick(View v) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                DataSingleton.getInstance().getFriendById(id).setBirthday(String.valueOf(dayOfMonth) + "/" + String.valueOf(month+1));
            }
        };

        DatePickerDialog d = new DatePickerDialog(
                context, android.R.style.Theme_Holo_Light_Dialog_MinWidth, listener, year, month,day);

        d.show();
    }
}
