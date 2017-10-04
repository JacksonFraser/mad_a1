package com.example.s3529589.mad_a1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.s3529589.mad_a1.Controller.meetingControllers.DisplayMapController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingDisplayLongClickController;
import com.example.s3529589.mad_a1.Database.FriendTable;
import com.example.s3529589.mad_a1.Database.MeetingFriendTable;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.Meeting;
import com.example.s3529589.mad_a1.Model.MeetingFriend;
import com.example.s3529589.mad_a1.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CustomMeetingDetailsArrayAdapter extends ArrayAdapter<Meeting> {
    private List<Meeting> meetingList;
    private Context context;
    private static LayoutInflater inflater = null;
    private Activity activity;

    public CustomMeetingDetailsArrayAdapter(Activity activity, List<Meeting> meetingList) {
        super(activity, 0, meetingList);
        this.meetingList = meetingList;
        this.context = activity;
        this.activity = activity;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        MeetingHolder holder = new MeetingHolder();

        View rowView = inflater.inflate(R.layout.meeting_details_list_view_item_row, null);
        rowView.setOnLongClickListener(new MeetingDisplayLongClickController(meetingList.get(pos).getId(), this));

        // Open map
        rowView.setOnClickListener(new DisplayMapController(meetingList.get(pos).getId(), this));

        holder.meetingTitleTV = (TextView) rowView.findViewById(R.id.meeting_title);
        holder.meetingTitleTV.setText(meetingList.get(pos).getTitle());


        holder.meetingStartTimeTV = (TextView) rowView.findViewById(R.id.meeting_start_time);


        //Formatted date for more readable display
        Date startDate = meetingList.get(pos).getStartTime();
        SimpleDateFormat s = new SimpleDateFormat("d-MMM-yyyy, h:mm:ss a");
        String startDateFormatted = s.format(startDate);
        System.out.println("WE GOT HERE " +startDateFormatted);
        holder.meetingStartTimeTV.setText("Start time: " + startDateFormatted);

        holder.meetingEndTimeTV = (TextView) rowView.findViewById(R.id.meeting_end_time);

        //Formatted date or more readable display
        Date endDate = meetingList.get(pos).getFinishTime();
        String endDateFormatted = s.format(endDate);
        holder.meetingEndTimeTV.setText("Finish time: " + endDateFormatted);

        holder.meetingLocationTV = (TextView) rowView.findViewById(R.id.meeting_location);
        holder.meetingLocationTV.setText("Location: " + meetingList.get(pos).getLocation());

        //create friends
        holder.meetingFriendsTV = (TextView) rowView.findViewById(R.id.meeting_friends);
        String friends = createFriendsString(meetingList.get(pos).getId());
        holder.meetingFriendsTV.setText(friends);
        return rowView;
    }

    private String createFriendsString(UUID meetingUUID) {
        String friendString = "";
        MeetingFriendTable meetingFriendTable =  new MeetingFriendTable();
        FriendTable friendTable = new FriendTable();
        System.out.println("THIS IS THE SIZE "+meetingFriendTable.getAllMeetingFriends().size());
        try {
            for (MeetingFriend m : meetingFriendTable.getAllMeetingFriends()) {
                if(m.getMeetingUUID().equals(meetingUUID)) {
                    for(Friend f : friendTable.getAllFriends()){
                        if(f.getId().equals(m.getFriendUUID())){
                            friendString = friendString.concat("- " + f.getName() + "\n");

                        }
                    }


                }
            }

            friendString = friendString.substring(0, friendString.length() - 1);
            return friendString;
        } catch (Exception e) {

        }
        return friendString;
    }

    public class MeetingHolder {
        TextView meetingTitleTV;
        TextView meetingStartTimeTV;
        TextView meetingEndTimeTV;
        TextView meetingFriendsTV;
        TextView meetingLocationTV;
    }

    public void updateItems(List<Meeting> meetingList){
        super.clear();
        if(meetingList.isEmpty()){
            notifyDataSetChanged();
        } else {
            this.meetingList = meetingList;

        }
        super.addAll(meetingList);
        notifyDataSetChanged();
    }

}
