package com.hismart.easylink.preview.ui.launch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hismart.easylink.preview.R;
import com.hismart.easylink.preview.ui.BaseToolbarCompatActivity;
import com.hismart.easylink.preview.util.LogUtils;

public class HisenseMallActivity extends BaseToolbarCompatActivity {
    WebView mWebView;
    WebView x5WebView;
    ContentLoadingProgressBar loadingProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hisense_mall);
        setLeftButtonIsBack(true);
        setLeftText("关闭");
        setLeftTextOnClickLisenter(new ToolBarTextClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
/*        mWebView = findViewById(R.id.web_view);
        initWebView();*/
        loadingProgressBar = findViewById(R.id.loading_progress);
        x5WebView = findViewById(R.id.web_view);
        initX5WebView();
        x5WebView.loadUrl("https://m.hisense.com");
    }


    private void initX5WebView() {
        x5WebView.getSettings().setJavaScriptEnabled(true);
        x5WebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains("http") || url.contains("https")) {
                    // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
                return true;
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                if (request.getUrl().toString().contains("http") || request.getUrl().toString().contains("https")) {
                    // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(request.getUrl().toString());
                    return true;
                }
                return true;
            }
        });
       // x5WebView.getSettings().setUserAgentString("userAgent");//根据不同用户设置
        x5WebView.getSettings().setSupportZoom(true);
        x5WebView.getSettings().setBuiltInZoomControls(true);
        x5WebView.getSettings().setUseWideViewPort(true);
        x5WebView.getSettings().setSavePassword(false);
        x5WebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);

        x5WebView.getSettings().setDisplayZoomControls(false);
        //android webview组件包含3个隐藏的系统接口：“accessibility”和和“ccessibilityaversal”以及“searchBoxJavaBridge_”，同样会造成远程代码执行。
        x5WebView.removeJavascriptInterface("accessibility");
        x5WebView.removeJavascriptInterface("accessibilityTraversal");
        x5WebView.removeJavascriptInterface("searchBoxJavaBridge_");
        //关闭自动播放，需要用户确认
        //三星S IV 4.2.2 不支持该方法
        try {
            x5WebView.getSettings().setMediaPlaybackRequiresUserGesture(true);
        } catch (NoSuchMethodError e) {

        }

        //To allow https to redirect to http you need to set the mixed content mode to MIXED_CONTENT_ALWAYS_ALLOW
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            x5WebView.getSettings().setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        WebSettings webSettings = x5WebView.getSettings();
        /*webSettings.setMinimumFontSize(minimumFontSize);
        webSettings.setMinimumLogicalFontSize(minimumLogicalFontSize);
        webSettings.setDefaultFontSize(defaultFontSize);
        webSettings.setDefaultFixedFontSize(defaultFixedFontSize);*/
        //webSettings.setBuiltInZoomControls(true);
        /***打开本地缓存提供JS调用**/
        webSettings.setDomStorageEnabled(true);
        // Set cache size to 10 mb by default. should be more than enough
        webSettings.setAppCacheMaxSize(1024 * 1024 * 10);
        String appCaceDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCaceDir);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        //启用数据库
        webSettings.setDatabaseEnabled(true);
        //启用地理定位
        webSettings.setGeolocationEnabled(true);
        //设置定位的数据库路径
        String appDatabaseDir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setGeolocationDatabasePath(appDatabaseDir);
/*        x5WebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

            }
        });*/
        x5WebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    loadingProgressBar.setProgress(newProgress, true);
                }else {
                    loadingProgressBar.setProgress(newProgress);
                }

                if (newProgress >= 99) {
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                } else {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }





    private void initWebView() {
        //WebView加载web资源
        WebSettings webSettings = mWebView.getSettings();

        // 支持javascript
        webSettings.setJavaScriptEnabled(true);
        // 支持使用localStorage(H5页面的支持)
        webSettings.setDomStorageEnabled(true);
        // 支持数据库
        webSettings.setDatabaseEnabled(true);
        // 支持缓存
        webSettings.setAppCacheEnabled(true);
        String appCaceDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCaceDir);
        // 设置可以支持缩放
        webSettings.setUseWideViewPort(true);
        // 扩大比例的缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        // 隐藏缩放按钮
        webSettings.setDisplayZoomControls(false);
        // 自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        // 隐藏滚动条
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setVerticalScrollBarEnabled(false);
        // 进度显示及隐藏
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
/*                if (newProgress >= 99) {
                    pv.setVisibility(View.INVISIBLE);
                } else {
                    pv.setVisibility(View.VISIBLE);
                }*/
            }
        });

        // 处理网页内的连接（自身打开）
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains("http") || url.contains("https")) {
                    // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }

                return true;
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                if (request.getUrl().toString().contains("http") || request.getUrl().toString().contains("https")) {
                    // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(request.getUrl().toString());
                    return true;
                }
                return true;
            }
        });

        mWebView.loadUrl("https://m.hisense.com");
    }


    private void handleBackKeyEvent() {
        //当wmWebView不是处于第一页面时，返回上一个页面
        Log.d("123","mWebView ="+mWebView);

        if (x5WebView != null && x5WebView.canGoBack()) {
            x5WebView.goBack();
        } else {
            //当mWebView处于第一页面时,直接退出
            finish();
            //Intent intent = new Intent(HisenseMallActivity.this, MainActivity.class);
            //startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        handleBackKeyEvent();
    }

    @Override
    protected void pressToolbarNavigation() {
        handleBackKeyEvent();
    }

}
