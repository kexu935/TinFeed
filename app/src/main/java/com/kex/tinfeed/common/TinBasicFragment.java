package com.kex.tinfeed.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kex.tinfeed.R;
import com.kex.tinfeed.common.TinFragmentManager;

import java.util.UUID;

public class TinBasicFragment extends Fragment  {

    protected com.kex.tinfeed.common.TinFragmentManager tinFragmentManager;
    private final String uuid = UUID.randomUUID().toString();

    @CallSuper
    public void onAttach(Context context) {
        super.onAttach(context);
        tinFragmentManager = (com.kex.tinfeed.common.TinFragmentManager) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tin_basic, container, false);
    }

    public String getFragmentTag() {
        return this.getClass().getName() + uuid;
    }
}