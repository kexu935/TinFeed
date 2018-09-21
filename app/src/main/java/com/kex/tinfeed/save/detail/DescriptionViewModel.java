package com.kex.tinfeed.save.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kex.tinfeed.R;
import com.kex.tinfeed.common.BaseViewModel;

public class DescriptionViewModel extends com.kex.tinfeed.common.BaseViewModel<DescriptionViewModel.DescriptionViewHolder> {

    private final String description;

    DescriptionViewModel(String description) {
        super(R.layout.description_layout);
        this.description = description;
    }

    @Override
    public DescriptionViewHolder createItemViewHolder(View view) {
        return new DescriptionViewHolder(view);
    }

    @Override
    public void bindViewHolder(DescriptionViewHolder holder) {
        holder.description.setText(description);
    }

    static class DescriptionViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        DescriptionViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
        }
    }
}