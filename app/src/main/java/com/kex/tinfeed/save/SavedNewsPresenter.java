package com.kex.tinfeed.save;

import com.kex.tinfeed.retrofit.response.News;
import com.kex.tinfeed.save.SavedNewsContract;
import com.kex.tinfeed.save.SavedNewsModel;

import java.util.List;

public class SavedNewsPresenter implements SavedNewsContract.Presenter {
    private final SavedNewsContract.Model model;
    private SavedNewsContract.View view;

    SavedNewsPresenter() {
        model = new SavedNewsModel();
        model.setPresenter(this);
    }

    @Override
    public void onCreate() {}

    @Override
    public void onDestroy() {}

    @Override
    public void onViewAttached(SavedNewsContract.View view) {
        this.view = view;
        this.model.fetchData();
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void loadSavedNews(List<News> newsList) {
        if (view != null) {
            view.loadSavedNews(newsList);
        }
    }
}