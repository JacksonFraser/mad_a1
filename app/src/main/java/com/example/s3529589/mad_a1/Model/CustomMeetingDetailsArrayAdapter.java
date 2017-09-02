package com.example.s3529589.mad_a1.Model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingDisplayLongClickController;
import com.example.s3529589.mad_a1.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CustomMeetingDetailsArrayAdapter extends ArrayAdapter<Meeting> {
    private List<Meeting> meetingList;
    private Context context;
    private static LayoutInflater inflater = null;
    private Activity activity;

    public CustomMeetingDetailsArrayAdapter(Activity activity, List<Meeting> meetingList){
        super(activity, 0 ,meetingList);
        this.meetingList = meetingList;
        this.context = activity;
        this.activity = activity;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        MeetingHolder holder = new MeetingHolder();

        View rowView = inflater.inflate(R.layout.meeting_details_list_view_item_row, null);
        rowView.setOnLongClickListener(new MeetingDisplayLongClickController(meetingList.get(pos).getId(),this));

        holder.meetingTitleTV = (TextView) rowView.findViewById(R.id.meeting_title);
        holder.meetingTitleTV.setText(meetingList.get(pos).getTitle());


        holder.meetingStartTimeTV = (TextView) rowView.findViewById(R.id.meeting_start_time);

        //Formatted date for more readable display
        Date startDate = meetingList.get(pos).getStartTime();
        SimpleDateFormat s = new SimpleDateFormat("d-MMM-yyyy, h:mm a");
        String startDateFormatted = s.format(startDate);
        holder.meetingStartTimeTV.setText(startDateFormatted);

        holder.meetingEndTimeTV = (TextView) rowView.findViewById(R.id.meeting_end_time);


        //Formatted date or more readable display
        Date endDate = meetingList.get(pos).getEndTIme();
        String endDateFormatted = s.format(endDate);
        holder.meetingEndTimeTV.setText(endDateFormatted);

        return rowView;
    }

    public class MeetingHolder {
        TextView meetingTitleTV;
        TextView meetingStartTimeTV;
        TextView meetingEndTimeTV;
    }


}
