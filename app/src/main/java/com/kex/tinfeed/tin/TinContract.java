package com.kex.tinfeed.tin;

import com.kex.tinfeed.mvp.MvpContract;
import com.kex.tinfeed.retrofit.response.News;

import java.util.List;

public interface TinContract {
    interface View extends MvpContract.View<Presenter> {
        void showNewsCard(List<News> newsList);
        void onError();
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {
        void showNewsCard(List<News> newsList);
        void saveFavoriteNews(News news);
        void onOutOfCard();
        void onError();
    }

    interface Model extends MvpContract.Model<Presenter> {
        void fetchData(String country);
        void saveFavoriteNews(News news);
    }
}