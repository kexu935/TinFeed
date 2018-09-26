package com.kexu.tinfeed;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.kexu.tinfeed.common.TinBasicActivity;
import com.kexu.tinfeed.common.TinBasicFragment;

public class WebViewActivity extends TinBasicActivity implements PopupMenu.OnMenuItemClickListener {
    public static final String URL = "url";
    private String url = null;
    private ProgressBar progressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        progressBar = findViewById(R.id.progress_bar);
        final WebView webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Bundle innerBundle = bundle.getBundle(BUNDLE);
            if (innerBundle != null) {
                url = innerBundle.getString(URL);
                webView.loadUrl(url);
            }
        }

        findViewById(R.id.more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });
    }

    @SuppressLint("RestrictedApi")
    private void showMenu(View view) {
        PopupMenu menu = new PopupMenu(this, view);
        menu.setOnMenuItemClickListener(this);
        MenuInflater inflater = menu.getMenuInflater();
        inflater.inflate(R.menu.web_view_items, menu.getMenu());
        MenuPopupHelper menuHelper = new MenuPopupHelper(this, (MenuBuilder) menu.getMenu(), view);
        menuHelper.setForceShowIcon(true);
        menuHelper.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "From TinFeed: \n" + url;
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                this.startActivity(Intent.createChooser(sharingIntent, "Share TinFeed"));
                break;
            case R.id.menu_copy:
                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", url);
                clipboard.setPrimaryClip(clip);
                showSnackBar("Link copied！");
            default:
                break;
        }
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    public void doFragmentTransaction(TinBasicFragment basicFragment) {}

    @Override
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.snackbar), message, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.white));
        snackbar.show();
    }
}