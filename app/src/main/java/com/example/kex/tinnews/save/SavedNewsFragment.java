package com.example.kex.tinnews.save;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kex.tinnews.R;
import com.example.kex.tinnews.common.TinBasicFragment;
import com.example.kex.tinnews.save.detail.SavedNewsDetailedFragment;

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
        View view = inflater.inflate(R.layout.fragment_saved_news, container, false);
        TextView textView = view.findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinFragmentManager.doFragmentTransaction(SavedNewsDetailedFragment.newInstance());
            }
        });
        return view;
    }



}
