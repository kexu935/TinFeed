package com.kex.tinfeed.save;

import com.kex.tinfeed.mvp.MvpContract;
import com.kex.tinfeed.retrofit.response.News;

import java.util.List;

public interface SavedNewsContract {
    interface View extends com.kex.tinfeed.mvp.MvpContract.View<Presenter> {
        void loadSavedNews(List<News> newsList);
    }

    interface Presenter extends com.kex.tinfeed.mvp.MvpContract.Presenter<View, Model> {
        void loadSavedNews(List<News> newsList);
    }

    interface Model extends MvpContract.Model<Presenter> {
        void fetchData();
    }
}
