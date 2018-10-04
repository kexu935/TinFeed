package com.kexu.tinfeed.tin;

import com.kexu.tinfeed.mvp.MvpContract;
import com.kexu.tinfeed.retrofit.response.News;

import java.util.List;

public interface TinContract {
    interface View extends MvpContract.View<Presenter> {
        void showNewsCard(List<News> newsList, boolean isClear);
        void onError();
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {
        void showNewsCard(List<News> newsList, boolean isClear);
        void saveFavoriteNews(News news);
        void onOutOfCard();
        void onError();
    }

    interface Model extends MvpContract.Model<Presenter> {
        void fetchData(boolean isClear);
        void saveFavoriteNews(News news);
    }
}