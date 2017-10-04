package com.example.s3529589.mad_a1.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.s3529589.mad_a1.Model.MeetingFriend;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MeetingFriendTable {
    private static final String KEY_ID = "id";
    private static final String KEY_FRIEND_ID = "friend_id";
    private static final String KEY_MEETING_ID = "meeting_id";
    public static final String TABLE = "meetingfriend";

    public static String createTable() {
        return "CREATE TABLE " + TABLE +
                "(" + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_MEETING_ID + " TEXT,"
                + KEY_FRIEND_ID + " TEXT" + ")";
    }

    public void addMeetingFriend(MeetingFriend meetingFriend) {
        SQLiteDatabase db = DatabaseManagerSingleton.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MEETING_ID, String.valueOf(meetingFriend.getMeetingUUID()));
        values.put(KEY_FRIEND_ID, String.valueOf(meetingFriend.getFriendUUID()));

        // Inserting Row
        db.insert(TABLE, null, values);
        DatabaseManagerSingleton.getInstance().closeDatabase();
    }

    public List<MeetingFriend> getAllMeetingFriends() {
        List<MeetingFriend> meetingFriendList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE;

        SQLiteDatabase db = DatabaseManagerSingleton.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                MeetingFriend meetingFriend = new MeetingFriend();

                meetingFriend.setMeetingUUID(UUID.fromString(cursor.getString(1)));
                meetingFriend.setFriendUUID(UUID.fromString(cursor.getString(2)));


                // Adding contact to list
                meetingFriendList.add(meetingFriend);
            } while (cursor.moveToNext());
        }

        DatabaseManagerSingleton.getInstance().closeDatabase();
        // return contact list
        return meetingFriendList;
    }

    public void deleteMeetingFriendByFriendId(MeetingFriend meetingFriend) {
        SQLiteDatabase db = DatabaseManagerSingleton.getInstance().openDatabase();
        db.delete(TABLE, KEY_FRIEND_ID + " = ?" +
                        "AND " + KEY_MEETING_ID + " =?"
                ,
                new String[]{String.valueOf(meetingFriend.getFriendUUID()),
                        String.valueOf(meetingFriend.getMeetingUUID())});

        DatabaseManagerSingleton.getInstance().closeDatabase();
    }


}
