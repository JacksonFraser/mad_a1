package com.example.s3529589.mad_a1.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.example.s3529589.mad_a1.Controller.friendControllers.FriendMenuController;
import com.example.s3529589.mad_a1.Controller.meetingControllers.MeetingMenuController;
import com.example.s3529589.mad_a1.Database.DatabaseHandler;
import com.example.s3529589.mad_a1.Exceptions.InvalidMeetingInput;
import com.example.s3529589.mad_a1.Model.DataSingleton;
import com.example.s3529589.mad_a1.Model.Friend;
import com.example.s3529589.mad_a1.Model.Meeting;
import com.example.s3529589.mad_a1.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);



        DatabaseHandler db = new DatabaseHandler(this);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        db.addFriend(new Friend("Bobby Jarzombek", "gmail@gmail", date, 0, 0));
        db.addFriend(new Friend("Chris Dave", "newemail@me.com", date, 0, 0));
        db.addFriend(new Friend("Sally Sanders", "sally@me.com", date, 0, 0));

        // Reading all contacts
        List<Friend> friends = db.getAllFriends();

        for (Friend f : friends) {
            String log = "Id: "+f.getId()+", Name: " + f.getName() + ", Email: " + f.getEmail() + ", Date: " + f.getBirthdate() + " " +friends.size();
            // Writing Contacts to log
            Log.d("Entry number: ", log);
            db.deleteFriend(f);
        }



        addDummyData();
        View friendMenuBtn = findViewById(R.id.friendMenuBtn);
        friendMenuBtn.setOnClickListener(new FriendMenuController(this));



        View meetingMenuBtn = findViewById(R.id.meetingMenuBtn);
        meetingMenuBtn.setOnClickListener(new MeetingMenuController(this));
    }

    private void addDummyData() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Date date1 = calendar.getTime();
        Date date2 = calendar.getTime();
        Friend f1 = new Friend("Bobby Jarzombek", "gmail@gmail", date, 0, 0);
        Friend f2 = new Friend("Chris Dave", "newemail@me.com", date, 0, 0);
        Friend f3 = new Friend("Sally Sanders", "sally@me.com", date, 0, 0);
        Friend f4 = new Friend("dennis Le Mennis", "dennis@me.com", date, 0, 0);
        DataSingleton.getInstance().getFriendList().add(f1);
        DataSingleton.getInstance().getFriendList().add(f2);
        DataSingleton.getInstance().getFriendList().add(f3);
        DataSingleton.getInstance().getFriendList().add(f4);
        try {
            List<Friend> friendList1 = new ArrayList<>();

            friendList1.add(f1);
            friendList1.add(f2);
            friendList1.add(f3);
            friendList1.add(f4);

            List<Friend> friendList2 = new ArrayList<>();


            Meeting m1 = new Meeting("meeting 1", date1, date2, friendList1, "+330.1131, +11.11");
            Meeting m2 = new Meeting("meeting 2", date1, date2, friendList2, "+331.131, +111");
            DataSingleton.getInstance().getMeetingList().add(m1);
            DataSingleton.getInstance().getMeetingList().add(m2);

        } catch (InvalidMeetingInput e) {
            System.out.println(e.getMessage());
        }

    }

    /* Main activity code to see database entries

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        db.addContact(new Contact("Forest", "0412341234"));
        db.addContact(new Contact("Joseph", "0412341233"));
        db.addContact(new Contact("Bobby Shores", "0412344233"));

        // Reading all contacts
        List<Contact> contacts = db.getAllContacts();

        for (Contact contact : contacts) {
            String log = "Id: "+contact.getID()+", Name: " + contact.getName() + ", Phone: " + contact.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Entry number: ", log);
        }
    }
    */

    /* Contact class
    public class Contact {

        int id;
        String name;
        String phone_number;

        public Contact() {

        }

        public Contact(int id, String name, String phone_number){
            this.id = id;
            this.name = name;
            this.phone_number = phone_number;
        }

        public Contact(String name, String phone_number){
            this.name = name;
            this.phone_number = phone_number;
        }

        public int getID(){
            return this.id;
        }

        public void setID(int id){
            this.id = id;
        }

        public String getName(){
            return this.name;
        }

        public void setName(String name){
            this.name = name;
        }

        public String getPhoneNumber(){
            return this.phone_number;
        }

        public void setPhoneNumber(String phone_number){
            this.phone_number = phone_number;
        }
    }
     */

    /* DatabaseHandler class

    public class DatabaseHandler extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;

        // database name
        private static final String DATABASE_NAME = "contacts_db";

        // table name
        private static final String TABLE_CONTACTS = "contacts";

        // table columns names
        private static final String KEY_ID = "id";
        private static final String KEY_NAME = "name";
        private static final String KEY_PHONE = "phone";

        public DatabaseHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS +
                    "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PHONE + " TEXT" + ")";
            sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {
            // Drop older table if existed
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            onCreate(sqLiteDatabase);
        }

        // Adding new contact
        public void addContact(Contact contact) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, contact.getName());
            values.put(KEY_PHONE, contact.getPhoneNumber());

            // Inserting Row
            db.insert(TABLE_CONTACTS, null, values);
            db.close(); // Closing database connection
        }

        // Getting single contact
        public Contact getContact(int id) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                            KEY_NAME, KEY_PHONE }, KEY_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
            // return contact
            return contact;
        }

        // Getting All Contacts
        public List<Contact> getAllContacts() {
            List<Contact> contactList = new ArrayList<Contact>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    Contact contact = new Contact();
                    contact.setID(Integer.parseInt(cursor.getString(0)));
                    contact.setName(cursor.getString(1));
                    contact.setPhoneNumber(cursor.getString(2));
                    // Adding contact to list
                    contactList.add(contact);
                } while (cursor.moveToNext());
            }

            // return contact list
            return contactList;
        }

        // Getting contacts Count
        public int getContactsCount() {
            String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();

            // return count
            return cursor.getCount();
        }

        // Updating single contact
        public int updateContact(Contact contact) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, contact.getName());
            values.put(KEY_PHONE, contact.getPhoneNumber());

            // updating row
            return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                    new String[] { String.valueOf(contact.getID()) });

        }

        // Deleting single contact
        public void deleteContact(Contact contact) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                    new String[] { String.valueOf(contact.getID()) });
            db.close();
        }
    }

     */
}






