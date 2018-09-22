package com.kexu.tinfeed.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.kexu.tinfeed.retrofit.response.News;

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}