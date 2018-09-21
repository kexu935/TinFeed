package com.kex.tinfeed.save.detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kex.tinfeed.R;
import com.kex.tinfeed.common.BaseViewModel;
import com.kex.tinfeed.common.TinBasicFragment;
import com.kex.tinfeed.common.Util;
import com.kex.tinfeed.common.ViewModelAdapter;
import com.kex.tinfeed.retrofit.response.News;
import com.kex.tinfeed.save.detail.AuthorViewModel;
import com.kex.tinfeed.save.detail.DescriptionViewModel;
import com.kex.tinfeed.save.detail.ImageViewModel;
import com.kex.tinfeed.save.detail.ReadmoreViewModel;
import com.kex.tinfeed.save.detail.TitleViewModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SavedNewsDetailedFragment extends TinBasicFragment {

    private static final String NEWS = "NEWS";
    private ViewModelAdapter viewModelAdapter;

    public static com.kex.tinfeed.save.detail.SavedNewsDetailedFragment newInstance(News news) {
        Bundle args = new Bundle();
        args.putParcelable(NEWS, news);
        com.kex.tinfeed.save.detail.SavedNewsDetailedFragment fragment = new com.kex.tinfeed.save.detail.SavedNewsDetailedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_news_detailed, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModelAdapter = new ViewModelAdapter();
        recyclerView.setAdapter(viewModelAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadNews(Objects.requireNonNull(getArguments().getParcelable(NEWS)));
    }

    private void loadNews(News news) {
        List<BaseViewModel> viewModels = new LinkedList<>();
        if (!Util.isStringEmpty(news.title)) {
            viewModels.add(new TitleViewModel(news.title));
        }
        if (!Util.isStringEmpty(news.author) || !Util.isStringEmpty(news.time)) {
            viewModels.add(new AuthorViewModel(news.author, news.time));
        }
        if (!Util.isStringEmpty((news.image))) {
            viewModels.add(new ImageViewModel(news.image));
        }
        if (!Util.isStringEmpty(news.description)) {
            viewModels.add(new DescriptionViewModel(news.description));
        }
        if (!Util.isStringEmpty(news.url)) {
            viewModels.add(new ReadmoreViewModel(news.url, tinFragmentManager));
        }
        viewModelAdapter.addViewModels(viewModels);
    }
}