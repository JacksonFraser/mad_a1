package com.example.s3529589.mad_a1.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.s3529589.mad_a1.Model.Friend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FriendTable {
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    public static final String TABLE = "friends";

    public static String createTable() {
        return "CREATE TABLE " + TABLE +
                "(" + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_BIRTHDAY + " TEXT,"
                + KEY_LATITUDE + " TEXT,"
                + KEY_LONGITUDE + " TEXT" + ")";
    }

    public void addFriend(Friend friend) {
        SQLiteDatabase db = DatabaseManagerSingleton.getInstance().openDatabase();

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

        db.insert(TABLE, null, values);
        DatabaseManagerSingleton.getInstance().closeDatabase();
    }

    public void deleteFriend(Friend friend) {
        SQLiteDatabase db = DatabaseManagerSingleton.getInstance().openDatabase();
        db.delete(TABLE, KEY_ID + " = ?",
                new String[]{String.valueOf(friend.getId())});
        DatabaseManagerSingleton.getInstance().closeDatabase();
    }

    public List<Friend> getAllFriends() {
        List<Friend> friendList = new ArrayList<Friend>();
        String selectQuery = "SELECT  * FROM " + TABLE;

        SQLiteDatabase db = DatabaseManagerSingleton.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Friend friend = new Friend();

                // set the friend's variables
                friend.setId(UUID.fromString(cursor.getString(0)));
                friend.setName(cursor.getString(1));
                friend.setEmail(cursor.getString(2));

                String dateString = cursor.getString(3);

                Date date = null;
                try {
                    date = new SimpleDateFormat("d-MMM-yyyy, h:mm:ss a").parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                friend.setBirthday(date);

                friend.setLat(cursor.getDouble(4));
                friend.setLon(cursor.getDouble(5));
                friendList.add(friend);

            } while (cursor.moveToNext());
        }
        DatabaseManagerSingleton.getInstance().closeDatabase();
        return friendList;
    }

    public void updateFriend(String id, String name, String email ) {
        SQLiteDatabase db = DatabaseManagerSingleton.getInstance().openDatabase();

        ContentValues values = new ContentValues();

        if (!name.isEmpty())
            values.put(KEY_NAME, name);
        if (!email.isEmpty())
            values.put(KEY_EMAIL, email);

        if (name.isEmpty() && email.isEmpty()) {

        } else {
            db.update(TABLE, values, KEY_ID + " = ?",
                    new String[]{String.valueOf((UUID.fromString(id)))});
            DatabaseManagerSingleton.getInstance().closeDatabase();
        }
    }


}
