package com.example.kex.tinnews.tin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kex.tinnews.R;
import com.example.kex.tinnews.common.TinBasicFragment;
import com.example.kex.tinnews.retrofit.response.News;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TinGalleryFragment extends TinBasicFragment implements TinNewsCard.OnSwipeListener{

    private SwipePlaceHolderView mSwipeView;

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
        mSwipeView = view.findViewById(R.id.swipeView);

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tin_news_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tin_news_swipe_out_msg_view));

        view.findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        view.findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

        //fake date
        for (int i = 0; i < 10; i++) {
            News news = new News();
            news.image = "https://i.ytimg.com/vi/BgIJ45HKDpw/maxresdefault.jpg";
            TinNewsCard tinNewsCard = new TinNewsCard(news, mSwipeView, this);
            mSwipeView.addView(tinNewsCard);
        }

        return view;
    }

    @Override
    public void onLike(News news) {

    }
}
