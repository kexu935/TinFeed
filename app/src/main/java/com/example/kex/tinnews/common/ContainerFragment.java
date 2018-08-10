package com.example.kex.tinnews.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kex.tinnews.R;

public class ContainerFragment extends TinBasicFragment {
    private int pageIndex;

    public static ContainerFragment newInstance(int pageIndex) {
        ContainerFragment containerFragment = new ContainerFragment();
        containerFragment.pageIndex = pageIndex;
        return containerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        TextView textView = view.findViewById(R.id.text);
        textView.setText("This is containFragment" + pageIndex);
        return view;
    }
}
