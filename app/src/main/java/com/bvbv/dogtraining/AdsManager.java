package com.bvbv.dogtraining;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Balaji Vignesh on 13-Nov-16.
 */
public class AdsManager {
    // Static fields are shared between all instances.
    static InterstitialAd interstitialAd = null;

    public void createAd(Context context) {
        if(interstitialAd == null)
        {
            interstitialAd = new InterstitialAd(context);
            interstitialAd.setAdUnitId("ca-app-pub-8416049748850189/2123937759");
        }

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        interstitialAd.loadAd(adRequest);
    }

    public InterstitialAd getAd() {
        return interstitialAd;
    }
}
