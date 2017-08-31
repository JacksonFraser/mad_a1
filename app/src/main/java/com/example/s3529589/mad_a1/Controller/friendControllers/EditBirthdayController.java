package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import com.example.s3529589.mad_a1.Model.DataSingleton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditBirthdayController implements View.OnClickListener {
    private Context context;
    private int id;

    public EditBirthdayController(Context context, int id) {
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
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                Date date = calendar.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String birthday = formatter.format(date);

                DataSingleton.getInstance().getFriendById(id).setBirthday(birthday);
            }
        };

        DatePickerDialog d = new DatePickerDialog(
                context, android.R.style.Theme_Holo_Light_Dialog_MinWidth, listener, year, month,day);

        d.show();
    }
}
