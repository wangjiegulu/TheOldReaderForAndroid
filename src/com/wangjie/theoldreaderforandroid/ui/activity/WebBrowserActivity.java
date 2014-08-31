package com.wangjie.theoldreaderforandroid.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.wangjie.androidinject.annotation.annotations.base.AILayout;
import com.wangjie.androidinject.annotation.annotations.base.AIView;
import com.wangjie.theoldreaderforandroid.R;
import com.wangjie.theoldreaderforandroid.ui.base.BaseActivity;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/31/14.
 */
@AILayout(R.layout.web_browser)
public class WebBrowserActivity extends BaseActivity{
    public static final String TAG = WebBrowserActivity.class.getSimpleName();

    @AIView(R.id.web_browser_wv)
    private WebView webView;
    @AIView(R.id.web_browser_pb)
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        initWebClient();


    }

    private void initWebClient(){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setDefaultTextEncodingName("UTF-8");
//        settings.setBlockNetworkImage(true);

        /**
         * 39 适应竖屏
         * 57 适应横屏
         */
        webView.setInitialScale(39);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.loadUrl(
                getIntent().getStringExtra("url")
        );

        webView.setWebViewClient(webViewClient);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
    }


    WebViewClient webViewClient = new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(context, description, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            getSupportActionBar().setTitle(view.getTitle());
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_web_browser_back:
                if(webView.canGoBack()){
                    webView.goBack();
                }
                break;
            case R.id.menu_web_browser_forward:
                if(webView.canGoForward()){
                    webView.goForward();
                }
                break;
            case R.id.menu_web_browser_refresh:
                webView.reload();
                break;
        }

        return super.onOptionsItemSelected(item);
    }




}
