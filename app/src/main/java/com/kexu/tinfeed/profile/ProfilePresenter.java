package com.kexu.tinfeed.profile;

import android.view.View;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View view;
    private ProfileContract.Model model;

    ProfilePresenter() {
        this.model = new ProfileModel();
        this.model.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewAttached(ProfileContract.View view) {
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
    public View.OnClickListener getCacheClearListener() {
        return view -> {
            model.deleteAllNewsCache();
        };
    }
}