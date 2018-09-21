package com.kex.tinfeed.profile;

import android.annotation.SuppressLint;

import com.kex.tinfeed.TinApplication;
import com.kex.tinfeed.database.AppDatabase;
import com.kex.tinfeed.profile.CountryEvent;
import com.kex.tinfeed.profile.ProfileContract;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfileModel implements com.kex.tinfeed.profile.ProfileContract.Model {
    private com.kex.tinfeed.profile.ProfileContract.Presenter presenter;
    private com.kex.tinfeed.database.AppDatabase db = com.kex.tinfeed.TinApplication.getDataBase();

    @Override
    public void setPresenter(com.kex.tinfeed.profile.ProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteAllNewsCache() {
        Completable.fromAction(() -> db.newsDao().deleteAllNews()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
            presenter.onCacheCleared();
        }, error -> {});
    }

    @Override
    public void setDefaultCountry(String country) {
        EventBus.getDefault().post(new com.kex.tinfeed.profile.CountryEvent(country));
        presenter.onCountryChanged(country);
    }
}