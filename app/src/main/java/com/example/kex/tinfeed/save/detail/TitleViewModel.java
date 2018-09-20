package com.example.kex.tinfeed.save.detail;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kex.tinfeed.R;
import com.example.kex.tinfeed.common.BaseViewModel;

public class TitleViewModel extends BaseViewModel<TitleViewModel.TitleViewModelHolder> {
    private String title;
    TitleViewModel(String title) {
        super(R.layout.title_layout);
        this.title = title;
    }

    public TitleViewModel(String title, @LayoutRes int layout) {
        super(layout);
        this.title = title;
    }

    @Override
    public TitleViewModelHolder createItemViewHolder(View view) {
        return new TitleViewModelHolder(view);
    }

    @Override
    public void bindViewHolder(TitleViewModelHolder holder) {
        holder.title.setText(title);
    }

    static class TitleViewModelHolder extends RecyclerView.ViewHolder {
        TextView title;
        TitleViewModelHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}