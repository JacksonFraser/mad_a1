package com.example.s3529589.mad_a1.Database;

import com.example.s3529589.mad_a1.Model.Friend;

/**
 * Created by s3529589 on 10/2/17.
 */

public class FriendTable {


    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    public static final String TABLE= "friends";

    public static String createTable() {
        return "CREATE TABLE " + TABLE +
                "(" + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_BIRTHDAY + " TEXT,"
                + KEY_LATITUDE + " TEXT,"
                + KEY_LONGITUDE + " TEXT" + ")";
    }

    public void insert(Friend f) {

        //SQLiteDatabase db = Da

        /*
        *
        *
        *  // CREATE
    public void addFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
http://instinctcoder.com/android-studio-sqlite-database-multiple-tables-example/
        ContentValues values = new ContentValues();
        values.put(KEY_ID, String.valueOf(friend.getId()));
        values.put(KEY_NAME, friend.getName());
        values.put(KEY_EMAIL, friend.getEmail());

        // format birthday into string before entering into SQLite db
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-yyyy, h:mm:ss a");
        Date friendBirthdate = friend.getBirthdate();
        String dateString = dateFormat.format(friendBirthdate);
        values.put(KEY_BIRTHDAY, dateString);

        values.put(KEY_LONGITUDE, friend.getLon());
        values.put(KEY_LATITUDE, friend.getLat());

        db.insert(TABLE_FRIENDS, null, values);
        db.close();
    }
        *
        *
        * */
    }

}
