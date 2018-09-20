package com.example.kex.tinfeed.tin;

import com.example.kex.tinfeed.profile.CountryEvent;
import com.example.kex.tinfeed.retrofit.response.News;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class TinPresenter implements TinContract.Presenter {

    private TinContract.View view;
    private TinContract.Model model;
    private String country;
    private static final String INIT_COUNTRY = "us";

    TinPresenter() {
        this.model = new TinModel();
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
    public void onEvent(CountryEvent countryEvent) {
        if (this.view != null) {
            this.country = countryEvent.country;
            this.model.fetchData(country);
        }
    }

    @Override
    public void onViewAttached(TinContract.View view) {
        this.view = view;
        this.model.fetchData(this.country);
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void showNewsCard(List<News> newsList) {
        if (view != null) {
            view.showNewsCard(newsList);
        }
    }

    @Override
    public void saveFavoriteNews(News news) {
        model.saveFavoriteNews(news);
    }

    @Override
    public void onOutOfCard() {
        if (this.view != null) {
            this.model.fetchData(this.country);
        }
    }

    @Override
    public void onError() {
        if (view != null) {
            view.onError();
        }
    }
}
