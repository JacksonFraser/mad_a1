package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.s3529589.mad_a1.Activity.friendActivities.DatePickerActivity;
import com.example.s3529589.mad_a1.Activity.friendActivities.FriendMenuActivity;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConfirmDateController implements View.OnClickListener {
    String name;
    String email;
    DatePicker datePicker;
    private DatePickerActivity datePickerActivity;

    public ConfirmDateController(String name, String email, DatePicker datePicker, DatePickerActivity datePickerActivity) {
        this.name = name;
        this.email = email;
        this.datePicker = datePicker;
        this.datePickerActivity = datePickerActivity;
    }

    @Override
    public void onClick(View v) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String birthday = formatter.format(date);

        // add to the Friends ArrayList
        DataSingleton.getInstance().getFriendList().add(new Friend(name, email, date));

        for(Friend f : DataSingleton.getInstance().getFriendList())
            System.out.println(f.getName());

        Intent it = new Intent(datePickerActivity, FriendMenuActivity.class);
        datePickerActivity.startActivity(it);

        //finish DatePickerActivity
        datePickerActivity.finish();
        Toast.makeText(this.datePickerActivity, R.string.friend_added_toast, Toast.LENGTH_SHORT).show();
    }
}
