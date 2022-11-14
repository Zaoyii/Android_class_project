package com.zcyi.classproject11_7;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  String content = "<div class=\"CommentContent css-1ygdre8\"><p>我认为通篇都在扯蛋..没写到重点上!</p><p><br></p><p>如果App中的webview和手机内置浏览器一模一样,为什么会出现各种各样的BUG?就连支付证书都不支持,还是需要app端自己写方法调用...</p><p><br></p><p>说白了就是APP内的一个容器的概念... 虽然我不是andriod开发,但嵌入式H5页面写的也绝对不少...</p><p><br></p><p>就是一个阉割版本的浏览器... 都不具备真正浏览器内核的东西! </p></div>";
        //String url = "http://10.176.128.195:8080";
        String urlBaidu = "https://www.wtu.edu.cn/";

        Glide.with(this).load("http://10.177.7.73:8080/myweb/goods/img/oven.png").fitCenter().into((ImageView) findViewById(R.id.img));

        webView = (WebView) findViewById(R.id.zcyi);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(String.valueOf(request.getUrl()));
                return true;
            }
        });

        //webView.loadUrl(url);

        webView.loadUrl(urlBaidu);

        //webView.loadData(content, "text/html", "utf-8");

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoForward()) {
                    webView.goForward();
                }
            }
        });
        findViewById(R.id.pre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
            }
        }
        return true;
    }
}