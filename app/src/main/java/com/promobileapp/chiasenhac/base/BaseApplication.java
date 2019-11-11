package com.promobileapp.chiasenhac.base;

import android.app.Application;
import android.content.Context;

import com.promobileapp.chiasenhac.utils.AdsUtils;


public class BaseApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AdsUtils.getSharedInstance().setupInterstitialAd(this);
    }
}
