package com.example.kex.tinnews.save;

import com.example.kex.tinnews.mvp.MvpContract;

public interface SavedNewsContract {
    interface View extends MvpContract.View<Presenter> {
    }

    interface Presenter extends  MvpContract.Presenter<View, Model> {
    }

    interface Model extends MvpContract.Model<Presenter> {
    }
}
