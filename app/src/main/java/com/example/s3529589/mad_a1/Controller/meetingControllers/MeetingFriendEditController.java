package com.example.s3529589.mad_a1.Controller.meetingControllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.example.s3529589.mad_a1.Adapter.CustomMeetingDetailsArrayAdapter;
import com.example.s3529589.mad_a1.Database.FriendTable;
import com.example.s3529589.mad_a1.Database.MeetingFriendTable;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.MeetingFriend;

import java.util.UUID;


public class MeetingFriendEditController implements View.OnClickListener {
    private UUID id;
    private CustomMeetingDetailsArrayAdapter customMeetingDetailsArrayAdapter;

    public MeetingFriendEditController(UUID id, CustomMeetingDetailsArrayAdapter customMeetingDetailsArrayAdapter) {
        this.id = id;
        this.customMeetingDetailsArrayAdapter = customMeetingDetailsArrayAdapter;
    }

    @Override
    public void onClick(View v) {
        FriendTable friendTable = new FriendTable();
        final String[] friends = new String[friendTable.getAllFriends().size()];
        final UUID[] friendsId = new UUID[friendTable.getAllFriends().size()];
        boolean[] checkedItems = new boolean[friendTable.getAllFriends().size()];
        final MeetingFriendTable meetingFriendTable = new MeetingFriendTable();
        int i = 0;
        for (Friend f : friendTable.getAllFriends()) {
            friends[i] = f.getName();
            friendsId[i] = f.getId();
            for (MeetingFriend meetingFriend : meetingFriendTable.getAllMeetingFriends()) {
                if (meetingFriend.getFriendUUID().equals(f.getId())
                        && meetingFriend.getMeetingUUID().equals(id)) {
                    try {

                        checkedItems[i] = true;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }
            i++;
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(customMeetingDetailsArrayAdapter.getContext());
        builder.setTitle("Select Friends");

        builder.setMultiChoiceItems(friends, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                FriendTable friendTable = new FriendTable();
                MeetingFriendTable meetingFriendTable = new MeetingFriendTable();
                if (isChecked) {
                    for (Friend f : friendTable.getAllFriends()) {
                        if (f.getId().equals(friendsId[which])) {
                            MeetingFriend meetingFriend = new MeetingFriend(id, f.getId());
                            meetingFriendTable.addMeetingFriend(meetingFriend);
                            customMeetingDetailsArrayAdapter.notifyDataSetChanged();
                        }
                    }
                }
                if (!isChecked) {
                    for (Friend f : friendTable.getAllFriends()) {
                        MeetingFriend meetingFriend = new MeetingFriend(id,f.getId());
                        if (f.getId().equals(friendsId[which]) && meetingFriend.getMeetingUUID().equals(id) ) {
                            meetingFriendTable.deleteMeetingFriendByFriendId(meetingFriend);
                            customMeetingDetailsArrayAdapter.notifyDataSetChanged();
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
