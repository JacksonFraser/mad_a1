package com.example.s3529589.mad_a1.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.s3529589.mad_a1.Model.Friend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FriendDatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "friends_db";
    private static final String TABLE_FRIENDS = "friends";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";

    public FriendDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_FRIENDS +
                "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_NAME       + " TEXT,"
                    + KEY_EMAIL      + " TEXT,"
                    + KEY_BIRTHDAY   + " TEXT,"
                    + KEY_LATITUDE   + " TEXT,"
                    + KEY_LONGITUDE  + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);

        onCreate(sqLiteDatabase);
    }

    // CRUD OPERATIONS

    // CREATE
    public void addFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, String.valueOf(friend.getId()));
        values.put(KEY_NAME, friend.getName());
        values.put(KEY_EMAIL, friend.getEmail());

        // format birthday into string before entering into SQLite db
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-yyyy, h:mm:ss a");
        Date today = Calendar.getInstance().getTime();
        String dateString = dateFormat.format(today);
        values.put(KEY_BIRTHDAY, dateString);

        values.put(KEY_LONGITUDE, friend.getLon());
        values.put(KEY_LATITUDE, friend.getLat());

        // Inserting Row
        db.insert(TABLE_FRIENDS, null, values);
        db.close();
    }


    // RETRIEVE
    public Friend getFriend(UUID id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Date date = Calendar.getInstance().getTime();

        Cursor cursor = db.query(false, TABLE_FRIENDS, new String[] { KEY_ID, KEY_NAME, KEY_BIRTHDAY,
                KEY_LATITUDE, KEY_LONGITUDE }, KEY_ID + "=?", new String[] {String.valueOf(id) },
                null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-yyyy, h:mm:ss a");
        String dateString = cursor.getString(3);
        try {
             date = new SimpleDateFormat("d-MMM-yyyy, h:mm:ss a").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Friend friend = new Friend((cursor.getString(1)), cursor.getString(2),
                date, cursor.getDouble(4), cursor.getDouble(5));
        friend.setId(id);

        return friend;
    }

    // UPDATE
    public int updateFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, friend.getName());
        values.put(KEY_EMAIL, friend.getEmail());

        SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-yyyy, h:mm:ss a");
        Date friendBirthday = friend.getBirthdate();
        String birthdayString = dateFormat.format(friendBirthday);
        values.put(KEY_BIRTHDAY, birthdayString);

        values.put(KEY_LONGITUDE, friend.getLon());
        values.put(KEY_LATITUDE, friend.getLat());

        // updating row
        return db.update(TABLE_FRIENDS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(friend.getId())});
    }

    // DELETE
    public void deleteFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FRIENDS, KEY_ID + " = ?",
                new String[]{String.valueOf(friend.getId())});
        db.close();
    }

    // Get all friends
    public List<Friend> getAllFriends() {
        List<Friend> friendList = new ArrayList<Friend>();
        String selectQuery = "SELECT  * FROM " + TABLE_FRIENDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Friend friend = new Friend();

                // set the friend's variables
                friend.setId(UUID.fromString(cursor.getString(1)));
                friend.setName(cursor.getString(2));
                friend.setEmail(cursor.getString(3));

                String dateString = cursor.getString(4);

                Date date = null;
                try {
                    date = new SimpleDateFormat("d-MMM-yyyy, h:mm:ss a").parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                friend.setBirthday(date);

                friend.setLat(cursor.getDouble(5));
                friend.setLon(cursor.getDouble(6));
                friendList.add(friend);

            } while (cursor.moveToNext());
        }
        return friendList;
    }

    // Number of contacts in the friends table
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FRIENDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}