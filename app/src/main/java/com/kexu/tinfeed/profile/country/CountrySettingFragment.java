package com.kexu.tinfeed.profile.country;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kexu.tinfeed.R;
import com.kexu.tinfeed.mvp.MvpFragment;

import java.util.List;

public class CountrySettingFragment extends MvpFragment<CountrySettingContract.Presenter> implements CountrySettingContract.View{

    private ListView countryGroup;
    @NonNull
    public static CountrySettingFragment newInstance() {
        return new CountrySettingFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.country_setting_layout, container, false);
        countryGroup = view.findViewById(R.id.list_view);
        return view;
    }

    @Override
    public CountrySettingContract.Presenter getPresenter() {
        return new CountrySettingPresenter();
    }

    @Override
    public void loadCountries(List<Country> countryList) {
        ListViewAdapter adapter = new ListViewAdapter(countryList, getContext(), presenter.getCountryListener());
        countryGroup.setAdapter(adapter);
    }
}
