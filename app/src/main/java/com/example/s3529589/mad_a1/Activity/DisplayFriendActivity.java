package com.example.s3529589.mad_a1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.s3529589.mad_a1.Model.CustomArrayArrayAdapter;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.R;

public class DisplayFriendActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_friends);
        createListView();
    }

    private void createListView(){
        ListView lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(new CustomArrayArrayAdapter(this, DataSingleton.getInstance().getFriendList()));
    }

    @Override
    public void onBackPressed()
    {
        Intent it = new Intent(DisplayFriendActivity.this, FriendMenuActivity.class);
        startActivity(it);

        //finish DisplayFriendActivity
        finish();
    }

}
