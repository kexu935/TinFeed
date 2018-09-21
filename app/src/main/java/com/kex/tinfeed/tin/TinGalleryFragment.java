package com.kex.tinfeed.tin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kex.tinfeed.R;
import com.kex.tinfeed.mvp.MvpFragment;
import com.kex.tinfeed.retrofit.response.News;
import com.kex.tinfeed.tin.TinContract;
import com.kex.tinfeed.tin.TinNewsCard;
import com.kex.tinfeed.tin.TinPresenter;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.List;

public class TinGalleryFragment extends com.kex.tinfeed.mvp.MvpFragment<com.kex.tinfeed.tin.TinContract.Presenter> implements com.kex.tinfeed.tin.TinNewsCard.OnSwipeListener, com.kex.tinfeed.tin.TinContract.View{

    private SwipePlaceHolderView mSwipeView;
    private static final int DEFAULT_VIEW_COUNT = 3;
    private static final int DEFAULT_PADDING_TOP = 20;
    private static final float DEFAULT_RELATIVE_SCALE = 0.01f;

    public static TinGalleryFragment newInstance() {
        Bundle args = new Bundle();
        TinGalleryFragment fragment = new TinGalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tin_gallery, container, false);
        mSwipeView = view.findViewById(R.id.swipeView);

        mSwipeView.getBuilder()
                .setDisplayViewCount(DEFAULT_VIEW_COUNT)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(DEFAULT_PADDING_TOP)
                        .setRelativeScale(DEFAULT_RELATIVE_SCALE)
                        .setSwipeInMsgLayoutId(R.layout.tin_news_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tin_news_swipe_out_msg_view));

        view.findViewById(R.id.rejectBtn).setOnClickListener((View v) -> {
            mSwipeView.doSwipe(false);
        });
        view.findViewById(R.id.acceptBtn).setOnClickListener((View v) -> {
            mSwipeView.doSwipe(true);
        });

        return view;
    }


    @Override
    public void showNewsCard(List<com.kex.tinfeed.retrofit.response.News> newsList) {
        mSwipeView.removeAllViews();
        for (com.kex.tinfeed.retrofit.response.News news : newsList) {
            com.kex.tinfeed.tin.TinNewsCard tinNewsCard = new com.kex.tinfeed.tin.TinNewsCard(news, mSwipeView, this);
            mSwipeView.addView(tinNewsCard);
        }
    }

    @Override
    public void onError() {
        tinFragmentManager.showSnackBar("News has been inserted before");
    }

    @Override
    public void onLike(com.kex.tinfeed.retrofit.response.News news) {
        presenter.saveFavoriteNews(news);
        tinFragmentManager.showSnackBar("Saving news...");
        if (mSwipeView.getChildCount() == 1) {
            presenter.onOutOfCard();
        }
    }

    @Override
    public com.kex.tinfeed.tin.TinContract.Presenter getPresenter() {
        return new com.kex.tinfeed.tin.TinPresenter();
    }
}