package com.kex.tinfeed;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.kex.tinfeed.database.AppDatabase;
import com.facebook.stetho.Stetho;

import org.jetbrains.annotations.Contract;

public class TinApplication extends Application {

    public static com.kex.tinfeed.database.AppDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        database = Room.databaseBuilder(getApplicationContext(),
                com.kex.tinfeed.database.AppDatabase.class, "tin_db").build();
    }

    @Contract(pure = true)
    public static com.kex.tinfeed.database.AppDatabase getDataBase() {
        return database;
    }
}