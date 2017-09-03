package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.s3529589.mad_a1.Activity.friendActivities.DatePickerActivity;
import com.example.s3529589.mad_a1.Activity.friendActivities.FriendMenuActivity;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.DummyLocationService;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConfirmDateController implements View.OnClickListener {
    private static final String LOG_TAG = ConfirmDateController.class.getName();
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
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String birthday = formatter.format(date);

        // DUMMY DATA STUFF
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dummyFormat = new SimpleDateFormat("h:mm:ss a");
        String timeInDataFormat = dummyFormat.format(currentTime);

        DummyLocationService dummyLocationService = DummyLocationService.getSingletonInstance(datePickerActivity);
        List<DummyLocationService.FriendLocation> matched = null;

        double latitude = 0;
        double longitude = 0;

        try
        {
            // 2 mins either side of timeInDataFormat
            matched = dummyLocationService.getFriendLocationsForTime(DateFormat.getTimeInstance(
                    DateFormat.MEDIUM).parse(timeInDataFormat), 2, 0);

            latitude = matched.get(0).latitude;
            longitude = matched.get(0).longitude;
        }
        catch (ParseException e)
        {

        }

        // add to the Friends ArrayList
        DataSingleton.getInstance().getFriendList().add(new Friend(name, email, date, latitude, longitude));
        DataSingleton.getInstance().getFriendList().get(4);

        Intent it = new Intent(datePickerActivity, FriendMenuActivity.class);
        datePickerActivity.startActivity(it);

        //finish DatePickerActivity
        datePickerActivity.finish();
        Toast.makeText(this.datePickerActivity, R.string.friend_added_toast, Toast.LENGTH_SHORT).show();
    }


}
