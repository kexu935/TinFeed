package com.example.kex.tinnews;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {
    public static final String URL = "url";
    private String url = null;
    private ProgressBar progressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
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
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            Bundle innerBundle = bundle.getBundle(BUNDLE);
//            if (innerBundle != null) {
//                url = innerBundle.getString(URL);
//                webView.loadUrl(url);
//            }
//        }
        url = "http://www.foxnews.com";
        webView.loadUrl(url);

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

}
