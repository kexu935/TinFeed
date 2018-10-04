package com.kexu.tinfeed.profile.country;

import android.support.annotation.StringRes;

import com.kexu.tinfeed.mvp.MvpContract;

import java.util.List;

public interface CountrySettingContract {
    interface View extends MvpContract.View<Presenter> {
        void loadCountries(List<Country> countryList);
        String getString(@StringRes int stringRes);
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {
        ListViewAdapter.CountryListener getCountryListener();
        void setDefaultCountry(String country);
    }

    interface Model extends MvpContract.Model<Presenter> {
        void getSavedCountry();
        void setDefaultCountry(Country country);
    }
}