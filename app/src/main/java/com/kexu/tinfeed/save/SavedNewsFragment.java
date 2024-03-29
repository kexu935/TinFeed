package com.kexu.tinfeed.save;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kexu.tinfeed.R;
import com.kexu.tinfeed.common.ViewModelAdapter;
import com.kexu.tinfeed.mvp.MvpFragment;
import com.kexu.tinfeed.retrofit.response.News;

import java.util.LinkedList;
import java.util.List;

public class SavedNewsFragment extends MvpFragment<SavedNewsContract.Presenter> implements SavedNewsContract.View {

    private ViewModelAdapter savedNewsAdapter;
    private TextView emptyState;

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
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        emptyState = view.findViewById(R.id.empty_state);
        savedNewsAdapter = new ViewModelAdapter();
        recyclerView.setAdapter(savedNewsAdapter);
        return view;
    }

    @Override
    public SavedNewsContract.Presenter getPresenter() {
        return new SavedNewsPresenter();
    }

    @Override
    public void loadSavedNews(List<News> newsList) {
        if (newsList == null || newsList.size() == 0) {
            emptyState.setVisibility(View.VISIBLE);
        } else {
            emptyState.setVisibility(View.GONE);
        }
        if (newsList != null) {
            List<SavedNewsViewModel> models = new LinkedList<>();
            for (News news : newsList) {
                models.add(0, new SavedNewsViewModel(news, tinFragmentManager));
            }
            savedNewsAdapter.addViewModels(models);
        }
    }
}