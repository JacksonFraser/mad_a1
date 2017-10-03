package com.example.s3529589.mad_a1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.s3529589.mad_a1.Model.Meeting;
import com.example.s3529589.mad_a1.Model.MeetingFriend;

/**
 * Created by s3529589 on 10/2/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATA_BASE_VERSION = 1;
    private static final String DATABASE_NAME = "mad_db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATA_BASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(FriendTable.createTable());
        db.execSQL(MeetingTable.createTable());
        db.execSQL(MeetingFriendTable.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FriendTable.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MeetingTable.TABLE);
    }
}
