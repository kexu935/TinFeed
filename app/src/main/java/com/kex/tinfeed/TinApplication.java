package com.kex.tinfeed;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.facebook.stetho.Stetho;
import com.kex.tinfeed.database.AppDatabase;

import org.jetbrains.annotations.Contract;

public class TinApplication extends Application {

    public static AppDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tin_db").build();
    }

    @Contract(pure = true)
    public static AppDatabase getDataBase() {
        return database;
    }
}