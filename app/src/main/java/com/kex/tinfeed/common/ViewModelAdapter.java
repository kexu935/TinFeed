package com.kex.tinfeed.common;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.kex.tinfeed.common.BaseViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ViewModelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<com.kex.tinfeed.common.BaseViewModel> viewModels;
    private final SparseArrayCompat<com.kex.tinfeed.common.BaseViewModel> viewTypeMap;

    public ViewModelAdapter() {
        viewModels = new ArrayList<>();
        viewTypeMap = new SparseArrayCompat<>();
    }

    public void addViewModels(Collection<? extends com.kex.tinfeed.common.BaseViewModel> viewModels) {
        this.viewModels.clear();
        this.viewTypeMap.clear();
        addAll(viewModels);
        notifyDataSetChanged();
    }

    public void addViewModel(com.kex.tinfeed.common.BaseViewModel viewModel) {
        this.viewModels.add(viewModel);
        viewTypeMap.put(viewModel.getViewType(), viewModel);
        int position = getPosition(viewModel);
        notifyItemInserted(position);
    }

    private void removeViewModel(int position) {
        if (position < -1 || position >= viewModels.size()) {
            return;
        }
        viewModels.remove(position);
        notifyItemRemoved(position);
    }

    private void removeViewModel(com.kex.tinfeed.common.BaseViewModel viewModel) {
        int position = getPosition(viewModel);
        removeViewModel(position);
    }

    private int getPosition(com.kex.tinfeed.common.BaseViewModel viewModel) {
        return viewModels.indexOf(viewModel);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewTypeMap.get(viewType).createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewModels.get(position).bindViewHolder(holder);
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewModels.get(position).getViewType();
    }

    private void addAll(Collection<? extends com.kex.tinfeed.common.BaseViewModel> viewModels) {
        if (viewModels == null) {
            return;
        }

        for (com.kex.tinfeed.common.BaseViewModel baseViewModel : viewModels) {
            this.viewModels.add(baseViewModel);

            //If there are multiple items of the same type the index will just update
            viewTypeMap.put(baseViewModel.getViewType(), baseViewModel);
        }
    }

    public boolean isEmpty() {
        return viewModels.isEmpty();
    }
}