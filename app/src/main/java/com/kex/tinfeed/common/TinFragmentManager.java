package com.kex.tinfeed.common;

import android.os.Bundle;

import com.kex.tinfeed.common.TinBasicFragment;

public interface TinFragmentManager {

    void doFragmentTransaction(TinBasicFragment basicFragment);

    void startActivityWithBundle(Class<?> clazz, boolean isFinished, Bundle bundle);

    void showSnackBar(String message);
}