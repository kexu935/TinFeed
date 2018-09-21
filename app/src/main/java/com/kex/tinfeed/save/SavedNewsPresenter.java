package com.kex.tinfeed.save;

import com.kex.tinfeed.retrofit.response.News;
import com.kex.tinfeed.save.SavedNewsContract;
import com.kex.tinfeed.save.SavedNewsModel;

import java.util.List;

public class SavedNewsPresenter implements com.kex.tinfeed.save.SavedNewsContract.Presenter {
    private final com.kex.tinfeed.save.SavedNewsContract.Model model;
    private com.kex.tinfeed.save.SavedNewsContract.View view;

    public SavedNewsPresenter() {
        model = new com.kex.tinfeed.save.SavedNewsModel();
        model.setPresenter(this);
    }

    @Override
    public void onCreate() {}

    @Override
    public void onDestroy() {}

    @Override
    public void onViewAttached(com.kex.tinfeed.save.SavedNewsContract.View view) {
        this.view = view;
        this.model.fetchData();
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void loadSavedNews(List<com.kex.tinfeed.retrofit.response.News> newsList) {
        if (view != null) {
            view.loadSavedNews(newsList);
        }
    }
}