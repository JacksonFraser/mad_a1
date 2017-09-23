package com.example.s3529589.mad_a1.Controller.friendControllers;

import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.s3529589.mad_a1.Activity.friendActivities.CreateFriendActivity;
import com.example.s3529589.mad_a1.Activity.friendActivities.FriendMenuActivity;
import com.example.s3529589.mad_a1.Model.DummyLocationService;
import com.example.s3529589.mad_a1.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConfirmDateController implements View.OnClickListener {
    String name;
    String email;
    DatePicker datePicker;
    private CreateFriendActivity datePickerActivity;

    public ConfirmDateController(String name, String email, DatePicker datePicker, CreateFriendActivity datePickerActivity) {
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
        catch (Exception e)
        {

        }

        datePickerActivity.createFriend(name, email, date, latitude, longitude);

        Intent it = new Intent(datePickerActivity, FriendMenuActivity.class);
        datePickerActivity.startActivity(it);

        //finish CreateFriendActivity
        datePickerActivity.finish();
        Toast.makeText(this.datePickerActivity, R.string.friend_added_toast, Toast.LENGTH_SHORT).show();
    }


}
