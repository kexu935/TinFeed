package com.example.kex.tinnews.save;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kex.tinnews.R;
import com.example.kex.tinnews.common.TinBasicFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedNewsFragment extends TinBasicFragment {


    public static SavedNewsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SavedNewsFragment fragment = new SavedNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_news, container, false);
    }

}
