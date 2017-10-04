package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.DatePicker;

import com.example.s3529589.mad_a1.Database.FriendDatabaseHandler;
import com.example.s3529589.mad_a1.Database.FriendTable;
import com.example.s3529589.mad_a1.Model.DataSingleton;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class EditBirthdayController implements View.OnClickListener {
    private Context context;
    private UUID id;

    public EditBirthdayController(Context context, UUID id) {
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
                FriendTable friendTable = new FriendTable();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                Date date = calendar.getTime();

                //DataSingleton.getInstance().getFriendById(id).setBirthday(date);
                //db.getFriend(id).setBirthday(date);
            }
        };

        DatePickerDialog d = new DatePickerDialog(
                context, android.R.style.Theme_Holo_Light_Dialog_MinWidth, listener, year, month, day);

        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d.show();
    }
}
