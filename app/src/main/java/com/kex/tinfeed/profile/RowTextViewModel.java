package com.kex.tinfeed.profile;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kex.tinfeed.R;
import com.kex.tinfeed.common.BaseViewModel;

import org.jetbrains.annotations.Contract;

public class RowTextViewModel extends BaseViewModel<RowTextViewModel.RowTextViewModelHolder> {

    private final String rowText;
    private final View.OnClickListener listener;
    private Integer flagImage;

    RowTextViewModel(String rowText, View.OnClickListener listener) {
        super(R.layout.setting_row_text_layout);
        this.rowText = getCountryName(rowText);
        this.listener = listener;
    }

    @NonNull
    @Contract(pure = true)
    private String getCountryName(String text) {
        switch (text) {
            case "us":
                flagImage = R.drawable.ic_list_us;
                return "United States";
            case "cn" :
                flagImage = R.drawable.ic_list_cn;
                return "China";
            case "de" :
                flagImage = R.drawable.ic_list_de;
                return "German";
            case "in" :
                flagImage = R.drawable.ic_list_in;
                return "India";
            default:
                return text;
        }
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