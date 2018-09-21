package com.kex.tinfeed;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.kex.tinfeed.R;
import com.kex.tinfeed.common.ContainerFragment;
import com.kex.tinfeed.common.TinBasicActivity;
import com.kex.tinfeed.common.TinBasicFragment;
import com.kex.tinfeed.common.TinFragmentPagerAdapter;

public class MainActivity extends com.kex.tinfeed.common.TinBasicActivity {
    private ViewPager viewPager;
    private BottomNavigationView bottomBar;
    private com.kex.tinfeed.common.TinFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //link viewpager, bottombar with view
        viewPager = findViewById(R.id.viewpager);
        adapter = new com.kex.tinfeed.common.TinFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(com.kex.tinfeed.common.TinFragmentPagerAdapter.FRAGMENT_NUMBER);

        bottomBar = findViewById(R.id.bottom_navigation);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                viewPager.setCurrentItem(com.kex.tinfeed.common.ContainerFragment.getPositionById(item.getItemId()));
                return true;
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    private FragmentManager getCurrentChildFragmentManager() {
        return adapter.getItem(viewPager.getCurrentItem()).getChildFragmentManager();
    }

    @Override
    public void doFragmentTransaction(com.kex.tinfeed.common.TinBasicFragment basicFragment) {
        FragmentTransaction fragmentTransaction = getCurrentChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(
                R.id.child_fragment_container,
                basicFragment,
                basicFragment.getFragmentTag()).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getCurrentChildFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.snackbar), message, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.white));
        snackbar.show();
    }
}