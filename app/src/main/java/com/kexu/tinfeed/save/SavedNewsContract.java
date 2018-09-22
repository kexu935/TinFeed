package com.kexu.tinfeed.save;

import com.kexu.tinfeed.mvp.MvpContract;
import com.kexu.tinfeed.retrofit.response.News;

import java.util.List;

public interface SavedNewsContract {
    interface View extends MvpContract.View<Presenter> {
        void loadSavedNews(List<News> newsList);
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {
        void loadSavedNews(List<News> newsList);
    }

    interface Model extends MvpContract.Model<Presenter> {
        void fetchData();
    }
}
