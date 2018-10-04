package com.kexu.tinfeed.profile.country;

import com.kexu.tinfeed.R;

import java.util.ArrayList;
import java.util.List;

public class CountrySettingPresenter implements CountrySettingContract.Presenter {

    private final CountrySettingContract.Model model;
    private CountrySettingContract.View view;

    CountrySettingPresenter() {
        model = new CountrySettingModel();
        model.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewAttached(CountrySettingContract.View view) {
        this.view = view;
        model.getSavedCountry();
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public ListViewAdapter.CountryListener getCountryListener() {
        return model::setDefaultCountry;
    }

    @Override
    public void setDefaultCountry(String country) {
        if (view != null) {
            List<Country> countryList = new ArrayList<>();
            countryList.add(new Country(R.drawable.ic_list_us, view.getString(R.string.us_country),
                    "us", "us".equals(country)));
            countryList.add(new Country(R.drawable.ic_list_cn, view.getString(R.string.cn_country),
                    "cn", "cn".equals(country)));
            countryList.add(new Country(R.drawable.ic_list_in, view.getString(R.string.in_country),
                    "in", "in".equals(country)));
            countryList.add(new Country(R.drawable.ic_list_de, view.getString(R.string.de_country),
                    "de", "de".equals(country)));
            countryList.add(new Country(R.drawable.ic_list_sg, view.getString(R.string.sg_country),
                    "sg", "sg".equals(country)));
            view.loadCountries(countryList);
        }
    }
}