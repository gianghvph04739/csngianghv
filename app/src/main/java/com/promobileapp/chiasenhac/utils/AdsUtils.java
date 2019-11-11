package com.promobileapp.chiasenhac.utils;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.promobileapp.chiasenhac.R;

public class AdsUtils {

    private static AdsUtils adsUtils;
    private InterstitialAd interstitialAd;
    private AdCloseListener adCloseListener;
    private boolean isReload = false;


    public static AdsUtils getSharedInstance() {
        if (adsUtils == null) {
            adsUtils = new AdsUtils();
        }
        return adsUtils;
    }

    public static final void clearAds() {
        adsUtils = null;
    }

    public void setupInterstitialAd(final Context context) {
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(context.getString(R.string.ads_interestial));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                if (adCloseListener != null) {
                    adCloseListener.onAdClose();
                }
                loadInterstitialAd();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                if (adCloseListener != null) {
                    adCloseListener.onAdFailed();
                }
                if (!isReload) {
                    isReload = true;
                    loadInterstitialAd();
                }
            }
        });
        loadInterstitialAd();
    }

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("A174FFE54763305DEE1A368DEBC6E790")
                .addTestDevice("BF40911D228C5923BC768BA4952E72DB")
                .addTestDevice("41A5F5829A5C76507048E4FF083EEBC8")
                .addTestDevice("775515D779B8C060E4EDB8EBCCF1A68A")
                .addTestDevice("E700E6CEFCA64A22043E719496C422D3")
                .addTestDevice("B3CB360DAD535AD348507077AD78AB53")
                .addTestDevice("18FA98E601EC28C72B6B08CB1C646D9B")
                .addTestDevice("C549AE4275A560FD36F744A55FD2F3CE")
                .addTestDevice("B4A8EE0CA3AA8CF1D7052739E9CCEBE6")
                .addTestDevice("1A2B69937B1F6D07C4DA806A41050567")
                .addTestDevice("2C7476EA1DA4285A017D7E6BC2D2D26D")
                .addTestDevice("329FFDA8ACB834DD091FA40FCB3F5B01")
                .addTestDevice("0DA519286DD23BD5892453508CCF0871")
                .addTestDevice("87C079BE7A1DE70E510A527B9745742C")
                .addTestDevice("B07361C4EB014EDA46D26C634D1086B8")
                .addTestDevice("1A2B69937B1F6D07C4DA806A41050567")
                .addTestDevice("B07361C4EB014EDA46D26C634D1086B8")
                .build();
        interstitialAd.loadAd(adRequest);
    }

    public void showInterstitialAd(AdCloseListener adCloseListener) {
        this.adCloseListener = adCloseListener;
        if (canShowInterstitialAd()) {
            isReload = false;
            interstitialAd.show();
        } else {
            loadInterstitialAd();
        }
    }

    public boolean canShowInterstitialAd() {
        return interstitialAd != null && interstitialAd.isLoaded();
    }

    public interface AdCloseListener {
        void onAdClose();

        void onAdFailed();
    }

}
