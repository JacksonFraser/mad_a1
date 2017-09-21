package com.example.s3529589.mad_a1.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.s3529589.mad_a1.Exceptions.InvalidMeetingInput;
import com.example.s3529589.mad_a1.Model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MeetingDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "meetings_db";
    private static final String TABLE_MEETINGS = "meetings";

    private static final String KEY_ID = "id";
    private static final String KEY_ID_PRIMARY = "id_primary";
    private static final String KEY_TITLE = "title";
    private static final String KEY_START_TIME = "start_time";
    private static final String KEY_END_TIME = "end_time";
    private static final String KEY_LOCATION = "location";

    public MeetingDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MEETINGS +
                "(" + KEY_ID_PRIMARY + " BLOB PRIMARY KEY,"
                + KEY_ID             + " INTEGER,"
                + KEY_TITLE          + " TEXT,"
                + KEY_START_TIME     + " TEXT,"
                + KEY_END_TIME       + " TEXT,"
                + KEY_LOCATION       + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEETINGS);

        onCreate(sqLiteDatabase);
    }

    // Adding new meeting
    public void addMeeting(Meeting meeting) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,         String.valueOf(meeting.getId()));
        values.put(KEY_TITLE,      meeting.getTitle());
        values.put(KEY_LOCATION,   meeting.getLocation());

        // format date to SQLite format before entering it into the DB
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

        Date start= Calendar.getInstance().getTime();
        Date end = Calendar.getInstance().getTime();

        String startString = dateFormat.format(start);
        String endString = dateFormat.format(end);

        values.put(KEY_START_TIME, startString);
        values.put(KEY_END_TIME,   endString);


        // Inserting Row
        db.insert(TABLE_MEETINGS, null, values);
        db.close();
    }

    /*
    // Getting single friend
    public Friend getFriend(UUID id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FRIENDS, new String[]{KEY_ID,
                        KEY_NAME, KEY_EMAIL, KEY_BIRTHDATE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        String dateString = cursor.getString(3);

        Date d = null;

        try {
            d = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.SSS").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Friend friend = new Friend(cursor.getString(1),cursor.getString(2), d, 0, 0);
        // return contact
        return friend;
    }
    */

    // Getting All Friends
    public List<Meeting> getAllMeetings() {
        List<Meeting> meetingList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MEETINGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Meeting meeting = new Meeting();
                meeting.setId(UUID.fromString(cursor.getString(1)));
                meeting.setTitle(cursor.getString(2));
                meeting.setLocation(cursor.getString(5));

                try {
                    Date  start = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(cursor.getString(3));
                    Date  end = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(cursor.getString(4));
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

        // return contact list
        return meetingList;
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MEETINGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single contact
    public int updateMeeting(Meeting meeting) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, meeting.getTitle());
        values.put(KEY_START_TIME, String.valueOf(meeting.getStartTime()));
        values.put(KEY_END_TIME, String.valueOf(meeting.getFinishTime()));
        values.put(KEY_LOCATION, String.valueOf(meeting.getLocation()));

        // updating row
        return db.update(TABLE_MEETINGS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(meeting.getId())});
    }

    // Deleting single friend
    public void deleteFMeeting(Meeting meeting) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEETINGS, KEY_ID + " = ?",
                new String[]{String.valueOf(meeting.getId())});
        db.close();
    }
}