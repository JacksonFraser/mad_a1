package com.example.s3529589.mad_a1.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.s3529589.mad_a1.Activity.DisplayFriendActivity;
import com.example.s3529589.mad_a1.R;

import java.util.List;

public class CustomArrayArrayAdapter extends ArrayAdapter<Friend> {
    private List<Friend> friendList;
    private Context context;

    private static LayoutInflater inflater = null;
    public CustomArrayArrayAdapter(DisplayFriendActivity displayFriendActivity, List<Friend> friendList) {
        super(displayFriendActivity, 0, friendList);
        this.friendList = friendList;
        this.context = displayFriendActivity;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        if(!context.getClass().isInstance(DisplayFriendActivity.class)){
            //holder.friendNameTV.setOnClickListener(RemoveDetailsController(friendList.get(pos.)));
        }

        return rowView;
    }

    public class FriendHolder {
        TextView friendNameTV;
        TextView friendEmailTV;
        TextView friendBirthDateTV;
    }
}
