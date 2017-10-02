package com.example.s3529589.mad_a1.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.s3529589.mad_a1.Exceptions.InvalidMeetingInput;
import com.example.s3529589.mad_a1.Model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by supriya on 2/10/17.
 */

public class MeetingTable {
    public static final String TABLE = "meetings";
    private static final String KEY_ID = "id";
    private static final String KEY_ID_PRIMARY = "id_primary";
    private static final String KEY_TITLE = "title";
    private static final String KEY_START_TIME = "start_time";
    private static final String KEY_END_TIME = "end_time";
    private static final String KEY_LOCATION = "location";


    public static String createTable(){
       return "CREATE TABLE " + TABLE +
                "(" + KEY_ID_PRIMARY + " BLOB PRIMARY KEY,"
                + KEY_ID             + " INTEGER,"
                + KEY_TITLE          + " TEXT,"
                + KEY_START_TIME     + " TEXT,"
                + KEY_END_TIME       + " TEXT,"
                + KEY_LOCATION       + " TEXT" + ")";
    }

    public void addMeeting(Meeting meeting) {
        SQLiteDatabase db = DatabaseManagerSingleton.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,         String.valueOf(meeting.getId()));
        values.put(KEY_TITLE,      meeting.getTitle());
        values.put(KEY_LOCATION,   meeting.getLocation());

        // format date to SQLite format before entering it into the DB
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date start= meeting.getStartTime();
        Date end = meeting.getFinishTime();

        String startString = dateFormat.format(start);
        String endString = dateFormat.format(end);

        values.put(KEY_START_TIME, startString);
        values.put(KEY_END_TIME,   endString);


        // Inserting Row
        db.insert(TABLE, null, values);
        DatabaseManagerSingleton.getInstance().closeDatabase();
    }

    public List<Meeting> getAllMeetings() {
        List<Meeting> meetingList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE;

        SQLiteDatabase db = DatabaseManagerSingleton.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Meeting meeting = new Meeting();
                meeting.setId(UUID.fromString(cursor.getString(1)));
                meeting.setTitle(cursor.getString(2));
                meeting.setLocation(cursor.getString(5));

                try {
                    System.out.println(("The string to parse: " + cursor.getString(3)));
                    Date  start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cursor.getString(3));
                    Date  end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cursor.getString(4));
                    meeting.setStartTime(start);
                    meeting.setFinishTime(end);
                } catch (ParseException e ) {
                    e.printStackTrace();
                } catch (InvalidMeetingInput invalidMeetingInput) {
                    invalidMeetingInput.printStackTrace();
                }

                // Adding contact to list
                meetingList.add(meeting);
            } while (cursor.moveToNext());
        }

        DatabaseManagerSingleton.getInstance().closeDatabase();
        // return contact list
        return meetingList;
    }

    public void updateMeeting(Meeting meeting) {
        SQLiteDatabase db = DatabaseManagerSingleton.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, meeting.getTitle());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = meeting.getStartTime();
        String startString = dateFormat.format(startTime);
        Date endTime = meeting.getFinishTime();
        String endString = dateFormat.format(endTime);
        values.put(KEY_START_TIME, startString);
        values.put(KEY_END_TIME, endString);
        values.put(KEY_LOCATION, String.valueOf(meeting.getLocation()));

        // updating row
        db.update(TABLE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(meeting.getId())});
        DatabaseManagerSingleton.getInstance().closeDatabase();
    }

    // Deleting single friend
    public void deleteMeeting(Meeting meeting) {
        SQLiteDatabase db = DatabaseManagerSingleton.getInstance().openDatabase();
        db.delete(TABLE, KEY_ID + " = ?",
                new String[]{String.valueOf(meeting.getId())});
        DatabaseManagerSingleton.getInstance().closeDatabase();
    }
}
