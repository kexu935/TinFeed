package com.kex.tinfeed.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kex.tinfeed.retrofit.response.News;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NewsDao {
    @Insert
    void insertNews(com.kex.tinfeed.retrofit.response.News news);

    @Query("SELECT * FROM news")
    Flowable<List<com.kex.tinfeed.retrofit.response.News>> getAll();

    @Query("DELETE FROM news")
    void deleteAllNews();
}
