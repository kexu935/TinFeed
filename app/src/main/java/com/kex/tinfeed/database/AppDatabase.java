package com.kex.tinfeed.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.kex.tinfeed.database.NewsDao;
import com.kex.tinfeed.retrofit.response.News;

@Database(entities = {com.kex.tinfeed.retrofit.response.News.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract com.kex.tinfeed.database.NewsDao newsDao();
}