package com.example.kex.tinfeed.tin;

import android.annotation.SuppressLint;

import com.example.kex.tinfeed.TinApplication;
import com.example.kex.tinfeed.database.AppDatabase;
import com.example.kex.tinfeed.retrofit.NewsRequestApi;
import com.example.kex.tinfeed.retrofit.RetrofitClient;
import com.example.kex.tinfeed.retrofit.response.News;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TinModel implements TinContract.Model {

    private TinContract.Presenter presenter;
    private final NewsRequestApi newsRequestApi;

    private final AppDatabase db;

    public TinModel() {
        newsRequestApi = RetrofitClient.getInstance().create(NewsRequestApi.class);
        db = TinApplication.getDataBase();
    }
    @Override
    public void setPresenter(TinContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @SuppressLint("CheckResult")
    @Override
    public void fetchData(String country) {
        newsRequestApi.getNewsByCountry(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(baseResponse -> baseResponse != null && baseResponse.articles != null)
                .subscribe(baseResponse -> {
                    presenter.showNewsCard(baseResponse.articles);
                });
    }


    @SuppressLint("CheckResult")
    @Override
    public void saveFavoriteNews(News news) {
        Completable.fromAction(() -> db.newsDao().insertNews(news)).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() ->{

        }, error -> {
                    presenter.onError();
        });
    }
}
