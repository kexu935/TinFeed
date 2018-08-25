package com.example.kex.tinnews;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.kex.tinnews.database.AppDatabase;
import com.facebook.stetho.Stetho;

public class TinApplication extends Application {

    public static AppDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "tin_db").build();
    }

    public static AppDatabase getDataBase() {
        return database;
    }
}