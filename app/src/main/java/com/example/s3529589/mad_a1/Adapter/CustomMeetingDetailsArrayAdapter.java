package com.example.s3529589.mad_a1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingDisplayLongClickController;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.Meeting;
import com.example.s3529589.mad_a1.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

        holder.meetingTitleTV = (TextView) rowView.findViewById(R.id.meeting_title);
        holder.meetingTitleTV.setText(meetingList.get(pos).getTitle());


        holder.meetingStartTimeTV = (TextView) rowView.findViewById(R.id.meeting_start_time);


/*






        // format date to SQLite format before entering it into the DB
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date start= meeting.getStartTime();
        Date end = meeting.getFinishTime();

        String startString = dateFormat.format(start);
        String endString = dateFormat.format(end);

        */




        //Formatted date for more readable display
        Date startDate = meetingList.get(pos).getStartTime();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        String friends = createFriendsString(meetingList.get(pos).getFriendList());
        holder.meetingFriendsTV.setText(friends);
        return rowView;
    }

    private String createFriendsString(List<Friend> friendList) {
        String friendString = "";

        try {
            for (Friend f : friendList) {
                friendString = friendString.concat("- " + f.getName() + "\n");
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
