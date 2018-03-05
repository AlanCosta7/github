package br.com.juventudesociedade.criative.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import br.com.juventudesociedade.criative.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalFragment extends Fragment {

    WebView wv;

    public PrincipalFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate ( R.layout.fragment_principal, container, false );
       WebView webView = (WebView)v.findViewById ( R.id.site_criative );
       webView.getSettings ().setJavaScriptEnabled ( true );
       webView.setWebViewClient ( new WebViewClient () );
       webView.loadUrl ( "http://criative.surge.sh/" );
       return v;
    }

}
