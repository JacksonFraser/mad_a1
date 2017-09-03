package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.example.s3529589.mad_a1.Activity.meetingActivities.ScheduleMeetingActivity;
import com.example.s3529589.mad_a1.Model.CustomMeetingDetailsArrayAdapter;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;

/**
 * Created by supriya on 3/09/17.
 */

public class MeetingFriendEditController implements View.OnClickListener {
    private int id;
    private CustomMeetingDetailsArrayAdapter customMeetingDetailsArrayAdapter;
    public MeetingFriendEditController(int id, CustomMeetingDetailsArrayAdapter customMeetingDetailsArrayAdapter) {
        this.id = id;
        this.customMeetingDetailsArrayAdapter = customMeetingDetailsArrayAdapter;
    }

    @Override
    public void onClick(View v) {
        final String[] friends = new String[DataSingleton.getInstance().getFriendList().size()];
        final int[] friendsId = new int[DataSingleton.getInstance().getFriendList().size()];
        boolean[] checkedItems = new boolean[DataSingleton.getInstance().getMeetingById(id).getFriendList().size()];
        int i = 0;
        for(Friend f : DataSingleton.getInstance().getFriendList()){
            friends[i] = f.getName();
            friendsId[i] = f.getId();
            if(DataSingleton.getInstance().getMeetingById(id).getFriendList().contains(f)){
                try{
                    checkedItems[i] = true;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            i++;
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(customMeetingDetailsArrayAdapter.getContext());
        builder.setTitle("Select Friends");
        builder.setMultiChoiceItems(friends, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    for(Friend f : DataSingleton.getInstance().getFriendList()){
                        if(f.getId() == friendsId[which]){
                            DataSingleton.getInstance().getMeetingById(id).getFriendList().add(f);
                        }
                    }
                }
                if(!isChecked){
                    for(Friend f : DataSingleton.getInstance().getFriendList()) {
                        if (f.getId() == friendsId[which]) {
                            DataSingleton.getInstance().getMeetingById(id).getFriendList().remove(f);
                        }
                    }
                }
            }
        })
                .setPositiveButton("Comfirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }
}
