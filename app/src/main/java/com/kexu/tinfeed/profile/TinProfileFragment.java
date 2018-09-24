package com.kexu.tinfeed.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kexu.tinfeed.R;
import com.kexu.tinfeed.common.ViewModelAdapter;
import com.kexu.tinfeed.mvp.MvpFragment;
import com.kexu.tinfeed.save.detail.TitleViewModel;

public class TinProfileFragment extends MvpFragment<ProfileContract.Presenter> implements ProfileContract.View {
    private ViewModelAdapter viewModelAdapter;
    public static final Country[] country = {Country.us, Country.cn, Country.de, Country.in, Country.sg};

    @NonNull
    public static TinProfileFragment newInstance() {
        return new TinProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tin_profile, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModelAdapter = new ViewModelAdapter();
        recyclerView.setAdapter(viewModelAdapter);
        return view;
    }

    @Override
    public ProfileContract.Presenter getPresenter() {
        return new ProfilePresenter();
    }

    @Override
    public void setView() {
        if (viewModelAdapter.isEmpty()) {
            viewModelAdapter.addViewModel(new TitleViewModel(getString(R.string.setting), R.layout.setting_title_layout));

            viewModelAdapter.addViewModel(new RowTextViewModel(null, getString(R.string.clear_cache), presenter.getCacheClearListener()));

            viewModelAdapter.addViewModel(new TitleViewModel(getString(R.string.change_country), R.layout.setting_title_layout));

            for (Country c : country) {
                viewModelAdapter.addViewModel(new RowTextViewModel(c.getCountryFlag(), getString(c.getCountry()), presenter.getOnCountryChangeListener(getString(c.getCountryUrl()))));
            }
        }
    }

    @Override
    public void onCacheCleared() {
        tinFragmentManager.showSnackBar("Saved news cleared!");
    }

    @Override
    public void onCountryChanged() {
        tinFragmentManager.showSnackBar("Country changed!");
    }
}