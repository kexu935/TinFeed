package com.kex.tinfeed.save.detail;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kex.tinfeed.R;
import com.kex.tinfeed.WebViewActivity;
import com.kex.tinfeed.common.BaseViewModel;
import com.kex.tinfeed.common.TinFragmentManager;

import static com.kex.tinfeed.WebViewActivity.URL;

public class ReadmoreViewModel extends com.kex.tinfeed.common.BaseViewModel<ReadmoreViewModel.ReadmoreViewModelHolder> {

    private final String uri;
    private final com.kex.tinfeed.common.TinFragmentManager fragmentManager;
    ReadmoreViewModel(String uri, com.kex.tinfeed.common.TinFragmentManager fragmentManager) {
        super(R.layout.readmore_layout);
        this.uri = uri;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ReadmoreViewModelHolder createItemViewHolder(View view) {
        return new ReadmoreViewModelHolder(view);
    }

    @Override
    public void bindViewHolder(ReadmoreViewModelHolder holder) {
        holder.readMore.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        holder.readMore.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString(URL, uri);
            fragmentManager.startActivityWithBundle(com.kex.tinfeed.WebViewActivity.class, false, bundle);
        });
    }

    static class ReadmoreViewModelHolder extends RecyclerView.ViewHolder {
        TextView readMore;
        ReadmoreViewModelHolder(View itemView) {
            super(itemView);
            readMore = itemView.findViewById(R.id.readmore);
        }
    }
}