package com.example.s3529589.mad_a1.Controller;

import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import com.example.s3529589.mad_a1.Activity.DatePickerActivity;
import com.example.s3529589.mad_a1.Activity.FriendMenuActivity;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;

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

        String birthday = datePicker.getDayOfMonth()+"/"+(datePicker.getMonth()+1);

        // add to the Friends ArrayList
        DataSingleton.getInstance().getFriendList().add(new Friend(name, email, birthday));

        for(Friend f : DataSingleton.getInstance().getFriendList())
            System.out.println(f.getName());

        Intent it = new Intent(datePickerActivity, FriendMenuActivity.class);
        datePickerActivity.startActivity(it);

        //finish DatePickerActivity
        datePickerActivity.finish();
    }

}
