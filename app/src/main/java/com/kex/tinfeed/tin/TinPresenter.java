package com.kex.tinfeed.tin;

import com.kex.tinfeed.profile.CountryEvent;
import com.kex.tinfeed.retrofit.response.News;
import com.kex.tinfeed.tin.TinContract;
import com.kex.tinfeed.tin.TinModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class TinPresenter implements com.kex.tinfeed.tin.TinContract.Presenter {

    private com.kex.tinfeed.tin.TinContract.View view;
    private com.kex.tinfeed.tin.TinContract.Model model;
    private String country;
    private static final String INIT_COUNTRY = "us";

    TinPresenter() {
        this.model = new com.kex.tinfeed.tin.TinModel();
        this.model.setPresenter(this);
        this.country = INIT_COUNTRY;
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
    public void onEvent(com.kex.tinfeed.profile.CountryEvent countryEvent) {
        if (this.view != null) {
            this.country = countryEvent.country;
            this.model.fetchData(country);
        }
    }

    @Override
    public void onViewAttached(com.kex.tinfeed.tin.TinContract.View view) {
        this.view = view;
        this.model.fetchData(this.country);
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void showNewsCard(List<com.kex.tinfeed.retrofit.response.News> newsList) {
        if (view != null) {
            view.showNewsCard(newsList);
        }
    }

    @Override
    public void saveFavoriteNews(com.kex.tinfeed.retrofit.response.News news) {
        model.saveFavoriteNews(news);
    }

    @Override
    public void onOutOfCard() {
        if (this.view != null) {
            this.model.fetchData(country);
        }
    }

    @Override
    public void onError() {
        if (view != null) {
            view.onError();
        }
    }
}