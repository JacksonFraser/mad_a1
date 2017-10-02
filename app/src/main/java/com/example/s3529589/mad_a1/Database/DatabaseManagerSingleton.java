package com.example.s3529589.mad_a1.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by s3529589 on 10/2/17.
 */

public class DatabaseManagerSingleton {

    private int counter = 0;

    private static DatabaseManagerSingleton instance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;


    public static synchronized void initialise(SQLiteOpenHelper helper) {
        if(instance == null) {
            instance = new DatabaseManagerSingleton();
            mDatabaseHelper = helper;
        }

    }

    public static synchronized DatabaseManagerSingleton getInstance(){
        if(instance == null){
            throw new IllegalStateException("Error. DatabaseManagerSingleton hasn't been initialised yet...");
        }
        return instance;
    }

    public synchronized SQLiteDatabase openDatabase(){
        counter++;
        if(counter == 1)
            mDatabase = mDatabaseHelper.getReadableDatabase();
        return mDatabase;
    }

    public synchronized void closeDatabase(){
        counter--;
        if(counter == 0)
            mDatabase.close();
    }
}
