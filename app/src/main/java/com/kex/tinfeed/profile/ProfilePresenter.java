package com.kex.tinfeed.profile;

import android.view.View;

import com.kex.tinfeed.profile.ProfileContract;
import com.kex.tinfeed.profile.ProfileModel;

public class ProfilePresenter implements com.kex.tinfeed.profile.ProfileContract.Presenter {
    private com.kex.tinfeed.profile.ProfileContract.View view;
    private com.kex.tinfeed.profile.ProfileContract.Model model;

    ProfilePresenter() {
        this.model = new com.kex.tinfeed.profile.ProfileModel();
        this.model.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewAttached(com.kex.tinfeed.profile.ProfileContract.View view) {
        this.view = view;
        this.view.setView();
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onCacheCleared() {
        if (view != null) {
            view.onCacheCleared();
        }
    }

    @Override
    public void onCountryChanged(String country) {
        if (view != null) {
            view.onCountryChanged(country);
        }
    }

    @Override
    public View.OnClickListener getCacheClearListener() {
        return view -> {
            model.deleteAllNewsCache();
        };
    }

    @Override
    public View.OnClickListener getOnCountryChangeListener(String country) {
        return view -> {
            model.setDefaultCountry(country);
        };
    }
}