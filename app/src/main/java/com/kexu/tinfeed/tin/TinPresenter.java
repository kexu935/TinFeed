package com.kexu.tinfeed.tin;

import android.app.Activity;

import com.kexu.tinfeed.profile.CountryEvent;
import com.kexu.tinfeed.retrofit.response.News;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class TinPresenter implements TinContract.Presenter {

    private TinContract.View view;
    private TinContract.Model model;
    private String country;

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
    public void onEvent(CountryEvent countryEvent) {
        if (this.view != null) {
            this.country = countryEvent.country;
            this.model.setCountry(this.country);
            this.model.fetchData(this.country, true);
        }
    }

    @Override
    public void onViewAttached(TinContract.View view) {
        this.view = view;
        this.model.fetchData(this.country, false);
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
            this.model.fetchData(country, false);
        }
    }

    @Override
    public void onError() {
        if (view != null) {
            view.onError();
        }
    }
}