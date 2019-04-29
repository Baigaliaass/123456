package com.example.chouqu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.chouqu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment {


    private View view;
    private WebView mWeb;

    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_web, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mWeb = (WebView) inflate.findViewById(R.id.web);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl("https://www.baidu.com/");
    }
}
