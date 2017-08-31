package com.example.s3529589.mad_a1.Activity.friendActivities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.s3529589.mad_a1.Model.CustomArrayAdapter;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.R;

import java.io.InputStream;

public class DisplayFriendActivity extends Activity{

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_friends);
        createListView();
    }

    public void passFriendID(int id){
        this.id = id;
    }

    private void createListView(){
        ListView lv = (ListView) findViewById(R.id.list_view);
        // show when the list is empty
        lv.setEmptyView(findViewById(R.id.list_view_empty));

        lv.setAdapter(new CustomArrayAdapter(this, DataSingleton.getInstance().getFriendList()));
    }

    @Override
    public void onBackPressed()
    {
        Intent it = new Intent(DisplayFriendActivity.this, FriendMenuActivity.class);
        startActivity(it);

        //finish DisplayFriendActivity
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 212) {
            if(resultCode == RESULT_OK){

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();

                Bitmap bitmapImage = BitmapFactory.decodeFile(filePath);
                Drawable displayImg = new BitmapDrawable(getResources(), bitmapImage);

                DataSingleton.getInstance().getFriendById(id).setDisplayPicture(displayImg);
            }
        }
    }
}
