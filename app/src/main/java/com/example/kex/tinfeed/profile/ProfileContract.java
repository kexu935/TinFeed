package com.example.kex.tinfeed.profile;

import com.example.kex.tinfeed.mvp.MvpContract;

public interface ProfileContract {
    interface View extends MvpContract.View<Presenter> {
        void setView();
        void onCacheCleared();
        void onCountryChanged(String country);
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {
        void onCacheCleared();
        void onCountryChanged(String country);
        android.view.View.OnClickListener getCacheClearListener();
        android.view.View.OnClickListener getOnCountryChangeListener(String country);
    }

    interface Model extends MvpContract.Model<Presenter> {
        void deleteAllNewsCache();
        void setDefaultCountry(String country);
    }
}