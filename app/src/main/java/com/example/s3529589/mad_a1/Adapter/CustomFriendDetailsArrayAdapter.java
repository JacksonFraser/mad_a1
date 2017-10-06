package com.example.s3529589.mad_a1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.s3529589.mad_a1.Controller.friendControllers.FriendMenuLongClickController;
import com.example.s3529589.mad_a1.Controller.friendControllers.PickDisplayPictureController;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class CustomFriendDetailsArrayAdapter extends ArrayAdapter<Friend> {
    private List<Friend> friendList;
    private Context context;




    public Activity getActivity() {
        return activity;
    }

    private Activity activity;
    private static LayoutInflater inflater = null;

    public CustomFriendDetailsArrayAdapter(Activity activity, List<Friend> friendList) {
        super(activity, 0, friendList);

        this.friendList = friendList;
        this.context = activity;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity = activity;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        FriendHolder holder = new FriendHolder();
        View rowView = inflater.inflate(R.layout.friend_details_list_iew_item_row, null);

        rowView.setOnLongClickListener(new FriendMenuLongClickController(friendList.get(pos).getId(), this));

        holder.friendNameTV = (TextView) rowView.findViewById(R.id.friendName);
        holder.friendNameTV.setText(friendList.get(pos).getName());

        holder.friendEmailTV = (TextView) rowView.findViewById(R.id.friendEmail);
        holder.friendEmailTV.setText(friendList.get(pos).getEmail());

        holder.friendBirthDateTV = (TextView) rowView.findViewById(R.id.friendBirthday);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String birthday = formatter.format(friendList.get(pos).getBirthdate());
        holder.friendBirthDateTV.setText(birthday);

        holder.friendLocation = (TextView) rowView.findViewById(R.id.friendLocation);
        holder.friendLocation.setText((friendList.get(pos).getLat() + ", " + friendList.get(pos).getLon()));

        holder.displayImg = (ImageView) rowView.findViewById(R.id.displayImg);
        holder.displayImg.setOnClickListener(new PickDisplayPictureController(this));

        return rowView;
    }

    public class FriendHolder {
        TextView friendNameTV;
        TextView friendEmailTV;
        TextView friendBirthDateTV;
        TextView friendLocation;
        ImageView displayImg;
    }

    public void updateItems(List<Friend> friendList){
        super.clear();
        if(friendList.isEmpty()){
            notifyDataSetChanged();
        } else {
            this.friendList = friendList;
        }
        super.addAll(friendList);
        notifyDataSetChanged();
    }



}
