package com.example.s3529589.mad_a1.Model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.s3529589.mad_a1.Controller.FriendLongCLickController;
import com.example.s3529589.mad_a1.Controller.RemoveFriendController;
import com.example.s3529589.mad_a1.R;

import java.util.List;

public class CustomArrayArrayAdapter extends ArrayAdapter<Friend> {
    private List<Friend> friendList;
    private Context context;

    public Activity getActivity() {
        return activity;
    }

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

        View rowView = inflater.inflate(R.layout.list_view_item_row, null);
        rowView.setOnClickListener(new RemoveFriendController(friendList.get(pos).getId(),this));
        rowView.setOnLongClickListener(new FriendLongCLickController(this));

        holder.friendNameTV = (TextView) rowView.findViewById(R.id.friendName);
        holder.friendNameTV.setText(friendList.get(pos).getName());


        holder.friendEmailTV = (TextView) rowView.findViewById(R.id.friendEmail);
        holder.friendEmailTV.setText(friendList.get(pos).getEmail());

        holder.friendBirthDateTV = (TextView) rowView.findViewById(R.id.friendBirthDate);
        holder.friendBirthDateTV.setText(friendList.get(pos).getBirthdate());

        return rowView;
    }



    public class FriendHolder {
        TextView friendNameTV;
        TextView friendEmailTV;
        TextView friendBirthDateTV;
    }
}
