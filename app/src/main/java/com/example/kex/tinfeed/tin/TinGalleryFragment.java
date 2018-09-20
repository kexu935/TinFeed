package com.example.kex.tinfeed.tin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kex.tinfeed.R;
import com.example.kex.tinfeed.common.TinBasicFragment;
import com.example.kex.tinfeed.mvp.MvpFragment;
import com.example.kex.tinfeed.retrofit.NewsRequestApi;
import com.example.kex.tinfeed.retrofit.RetrofitClient;
import com.example.kex.tinfeed.retrofit.response.News;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class TinGalleryFragment extends MvpFragment<TinContract.Presenter> implements TinNewsCard.OnSwipeListener, TinContract.View{

    private SwipePlaceHolderView mSwipeView;
    private int cardCount;

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
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
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

    private void updateCount() {
        if (--cardCount == 0) {
            presenter.onOutOfCard();
        }
    }

    @Override
    public void showNewsCard(List<News> newsList) {
        mSwipeView.removeAllViews();
        for (News news : newsList) {
            TinNewsCard tinNewsCard = new TinNewsCard(news, mSwipeView, this);
            mSwipeView.addView(tinNewsCard);
        }
        cardCount = newsList.size();
    }

    @Override
    public void onError() {
        tinFragmentManager.showSnackBar("News has been inserted before");
    }

    @Override
    public void onLike(News news) {
        presenter.saveFavoriteNews(news);
        updateCount();
        tinFragmentManager.showSnackBar("Saving news...");
    }

    @Override
    public TinContract.Presenter getPresenter() {
        return new TinPresenter();
    }
}
