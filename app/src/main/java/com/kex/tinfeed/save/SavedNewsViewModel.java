package com.kex.tinfeed.save;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kex.tinfeed.R;
import com.kex.tinfeed.common.BaseViewModel;
import com.kex.tinfeed.common.TinFragmentManager;
import com.kex.tinfeed.common.Util;
import com.kex.tinfeed.retrofit.response.News;
import com.kex.tinfeed.save.detail.SavedNewsDetailedFragment;

public class SavedNewsViewModel extends com.kex.tinfeed.common.BaseViewModel<SavedNewsViewModel.SavedNewsViewHolder> {

    private com.kex.tinfeed.common.TinFragmentManager tinFragmentManager;
    private com.kex.tinfeed.retrofit.response.News news;
    private static int[] ICON_ARRAY = new int[]{R.drawable.a_news_icon, R.drawable.g_news_icon,
            R.drawable.c_news_icon, R.drawable.y_news_icon, R.drawable.m_news_icon};

    public SavedNewsViewModel(com.kex.tinfeed.retrofit.response.News news, com.kex.tinfeed.common.TinFragmentManager tinFragmentManager) {
        super(R.layout.saved_news_item);
        this.news = news;
        this.tinFragmentManager = tinFragmentManager;
    }

    @Override
    public SavedNewsViewHolder createItemViewHolder(View view) {
        return new SavedNewsViewHolder(view);
    }

    @Override
    public void bindViewHolder(SavedNewsViewHolder holder) {
        if (!com.kex.tinfeed.common.Util.isStringEmpty(news.author)) {
            holder.author.setText(news.author);
        }
        holder.description.setText(news.getDescription());
        holder.icon.setImageResource(getDrawable());
        holder.itemView.setOnClickListener(v -> {
            tinFragmentManager.doFragmentTransaction(com.kex.tinfeed.save.detail.SavedNewsDetailedFragment.newInstance(news));
        });
    }

    private @DrawableRes
    int getDrawable() {
        return ICON_ARRAY[(int)(Math.random() * 5)];
    }

    public static class SavedNewsViewHolder extends RecyclerView.ViewHolder {

        TextView author;
        TextView description;
        ImageView icon;

        public SavedNewsViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.description);
            icon = itemView.findViewById(R.id.image);
        }
    }
}