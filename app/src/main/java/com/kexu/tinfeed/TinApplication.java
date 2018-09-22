package com.kexu.tinfeed;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;
import com.kexu.tinfeed.database.AppDatabase;

import org.jetbrains.annotations.Contract;

public class TinApplication extends Application {

    public static AppDatabase database;
    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tin_db").build();
        sharedPreferences = getApplicationContext().getSharedPreferences("Tin", Context.MODE_PRIVATE);
    }

    @Contract(pure = true)
    public static AppDatabase getDataBase() {
        return database;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}