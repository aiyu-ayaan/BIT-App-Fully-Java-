package com.aatec.bit.Bottom_Navigation.Erp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.aatec.bit.R;
import com.google.android.material.transition.platform.MaterialSharedAxis;

public class Erp extends Fragment {
    public static final String link = "https://erp.bitmesra.ac.in/iitmsv4eGq0RuNHb0G5WbhLmTKLmTO7YBcJ4RHuXxCNPvuIw=?enc=EGbCGWnlHNJ/WdgJnKH8DA==";
//            String link = "https://www.youtube.com/";
    public WebView myweb;
    private SwipeRefreshLayout swipeLayout;
    private ProgressBar progressBar;
    private RelativeLayout relativeLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, true));
        View v = inflater.inflate(R.layout.activity_erp, container, false);
        myweb = v.findViewById(R.id.myweb);
        swipeLayout = v.findViewById(R.id.swipe);
        progressBar = v.findViewById(R.id.pro);
        relativeLayout = v.findViewById(R.id.noInter);

        myweb.loadUrl(link);

        if (savedInstanceState != null) {
            myweb.restoreState(savedInstanceState);
        } else {
            myweb.getSettings().setJavaScriptEnabled(true);
            myweb.getSettings().setLoadWithOverviewMode(true);
            myweb.getSettings().setUseWideViewPort(true);
            myweb.getSettings().setDomStorageEnabled(true);
            myweb.getSettings().setLoadsImagesAutomatically(true);
            checkConnection();
        }


        myweb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                swipeLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        myweb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myweb.reload();
                checkConnection();
            }
        });
        return v;
    }

    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


        if (wifi.isConnected()) {
            myweb.loadUrl(link);
            myweb.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);


        } else if (mobileNetwork.isConnected()) {
            myweb.loadUrl(link);
            myweb.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        } else {

            myweb.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);

        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        myweb.saveState(outState);
    }
}
