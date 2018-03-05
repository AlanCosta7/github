package br.com.juventudesociedade.criative.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import br.com.juventudesociedade.criative.R;

public class PagamentoActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_pagamento );

        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        WebView webView = (WebView)findViewById ( R.id.site_pagseguro );
        webView.getSettings ().setJavaScriptEnabled ( true );
        webView.setWebViewClient ( new WebViewClient () );
        webView.loadUrl ( "https://docs.google.com/forms/d/e/1FAIpQLSf7JgosnVLaiNTS3fXwZrijN81x2r8N0x6nY_hgt3dpzYCg3w/viewform" );
        return;

    }

}
