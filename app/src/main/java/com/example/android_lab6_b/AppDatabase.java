package com.example.android_lab6_b;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Customer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static  AppDatabase database;
    public synchronized static AppDatabase getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"Test3").allowMainThreadQueries().build();

        }
        return database;
    }
    public  abstract CustomerDao customerDao();
}
