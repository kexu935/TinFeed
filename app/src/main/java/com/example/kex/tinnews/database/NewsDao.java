package com.example.kex.tinnews.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.kex.tinnews.retrofit.response.News;

@Dao
public interface NewsDao {
    @Insert
    void insertNews(News news);
}
