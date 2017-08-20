package com.example.s3529589.mad_a1.Model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.s3529589.mad_a1.Activity.DatePickerActivity;
import com.example.s3529589.mad_a1.Activity.DisplayFriendActivity;
import com.example.s3529589.mad_a1.Activity.MeetingMenuActivity;
import com.example.s3529589.mad_a1.Activity.RemoveFriendActivity;
import com.example.s3529589.mad_a1.Controller.RemoveFriendController;
import com.example.s3529589.mad_a1.R;

import java.util.List;

public class CustomArrayArrayAdapter extends ArrayAdapter<Friend> {
    private List<Friend> friendList;
    private Context context;
    private Activity activity;
    private static LayoutInflater inflater = null;


    public CustomArrayArrayAdapter(Activity activity, List<Friend> friendList) {
        super(activity, 0, friendList);

        this.friendList = friendList;
        this.context = activity;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity = activity;

    }



    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        FriendHolder holder = new FriendHolder();
        View rowView;

        rowView = inflater.inflate(R.layout.list_view_item_row, null);

        holder.friendNameTV = (TextView) rowView.findViewById(R.id.friendName);
        holder.friendNameTV.setText(friendList.get(pos).getName());


        holder.friendEmailTV = (TextView) rowView.findViewById(R.id.friendEmail);
        holder.friendEmailTV.setText(friendList.get(pos).getEmail());

        holder.friendBirthDateTV = (TextView) rowView.findViewById(R.id.friendBirthDate);
        holder.friendBirthDateTV.setText(friendList.get(pos).getBirthdate());


            String name = (String) holder.friendNameTV.getText().toString();
            holder.friendNameTV.setOnClickListener(new RemoveFriendController(friendList.get(pos).getId(),this));


        return rowView;
    }



    public class FriendHolder {
        TextView friendNameTV;
        TextView friendEmailTV;
        TextView friendBirthDateTV;
    }
}
