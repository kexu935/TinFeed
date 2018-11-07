package com.kexu.tinfeed.tin;

import com.kexu.tinfeed.profile.country.CountryChangeEvent;
import com.kexu.tinfeed.retrofit.response.News;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class TinPresenter implements TinContract.Presenter {

    private TinContract.View view;
    private TinContract.Model model;
    //private String country;

    TinPresenter() {
        this.model = new TinModel();
        this.model.setPresenter(this);
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CountryChangeEvent countryChangeEvent) {
        if (this.view != null) {
            this.model.fetchData(true);
        }
    }

    @Override
    public void onViewAttached(TinContract.View view) {
        this.view = view;
        this.model.fetchData(false);
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void showNewsCard(List<News> newsList, boolean isClear) {
        if (view != null) {
            view.showNewsCard(newsList, isClear);
        }
    }

    @Override
    public void saveFavoriteNews(News news) {
        model.saveFavoriteNews(news);
    }

    @Override
    public void onOutOfCard() {
        if (this.view != null) {
            this.model.fetchData(false);
        }
    }

    @Override
    public void onError() {
        if (view != null) {
            view.onError();
        }
    }

    @Override
    public void onNoInternetConnection() {
        if (view != null) {
            view.onNoInternetConnection();
        }
    }
}