package com.kexu.tinfeed.tin;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.kexu.tinfeed.TinApplication;
import com.kexu.tinfeed.database.AppDatabase;
import com.kexu.tinfeed.retrofit.NewsRequestApi;
import com.kexu.tinfeed.retrofit.RetrofitClient;
import com.kexu.tinfeed.retrofit.response.News;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TinModel implements TinContract.Model {

    private TinContract.Presenter presenter;
    private final NewsRequestApi newsRequestApi;
    private final AppDatabase db;
    private final SharedPreferences sharedPreferences;
    private static final String COUNTRY = "country";

    TinModel() {
        newsRequestApi = RetrofitClient.getInstance().create(NewsRequestApi.class);
        sharedPreferences = TinApplication.getSharedPreferences();
        db = TinApplication.getDataBase();
    }

    @Override
    public void setPresenter(TinContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @SuppressLint("CheckResult")
    @Override
    public void fetchData(boolean isClear) {
        Observable<String> observable = Observable.just(sharedPreferences.getString(COUNTRY, "us"));
        observable.flatMap(newsRequestApi::getNewsByCountry)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(baseResponse -> baseResponse != null && baseResponse.articles != null)
                .subscribe(baseResponse -> {
                    presenter.showNewsCard(baseResponse.articles, isClear);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveFavoriteNews(News news) {
        Completable.fromAction(() -> db.newsDao().
                insertNews(news)).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(() ->{
        }, error -> {
                    presenter.onError();
        });
    }
}