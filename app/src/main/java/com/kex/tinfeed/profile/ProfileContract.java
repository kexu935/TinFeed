package com.kex.tinfeed.profile;

import com.kex.tinfeed.mvp.MvpContract;

public interface ProfileContract {
    interface View extends com.kex.tinfeed.mvp.MvpContract.View<Presenter> {
        void setView();
        void onCacheCleared();
        void onCountryChanged(String country);
    }

    interface Presenter extends com.kex.tinfeed.mvp.MvpContract.Presenter<View, Model> {
        void onCacheCleared();
        void onCountryChanged(String country);
        android.view.View.OnClickListener getCacheClearListener();
        android.view.View.OnClickListener getOnCountryChangeListener(String country);
    }

    interface Model extends com.kex.tinfeed.mvp.MvpContract.Model<Presenter> {
        void deleteAllNewsCache();
        void setDefaultCountry(String country);
    }
}