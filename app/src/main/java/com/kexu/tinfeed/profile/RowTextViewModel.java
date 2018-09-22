package com.kexu.tinfeed.profile;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kexu.tinfeed.R;
import com.kexu.tinfeed.common.BaseViewModel;

public class RowTextViewModel extends BaseViewModel<RowTextViewModel.RowTextViewModelHolder> {

    private final String rowText;
    private final View.OnClickListener listener;
    private Integer flagImage;
    public static final Country[] country = {Country.us, Country.cn, Country.de, Country.in};

    RowTextViewModel(Integer rowFlag, String rowText, View.OnClickListener listener) {
        super(R.layout.setting_row_text_layout);
        this.flagImage = rowFlag;
        this.rowText = rowText;
        this.listener = listener;
    }

    @Override
    public RowTextViewModelHolder createItemViewHolder(View view) {
        return new RowTextViewModelHolder(view);
    }

    @Override
    public void bindViewHolder(RowTextViewModelHolder holder) {
        if (flagImage != null) {
            holder.flag.setImageResource(flagImage);
        } else {
            holder.flag.setVisibility(View.GONE);
        }
        holder.row.setText(rowText);
        holder.row.setOnClickListener(listener);
        holder.flag.setOnClickListener(listener);
    }

    static class RowTextViewModelHolder extends RecyclerView.ViewHolder {
        TextView row;
        ImageView flag;
        RowTextViewModelHolder(View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.flag);
            row = itemView.findViewById(R.id.row_text);
        }
    }
}