package com.karaokeapp;

import com.parse.Parse;

import android.app.Application;

/**
 * Created by yonatanl on 1/28/15.
 */
public class KaraokeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "dTaJAN6k8fHcjfy6ETBPYvPrABgVf1L40EVQLME0",
                "Nmjj8eCjcxFFYfVdlLPXJygxM4AXM32wUNQUM1Oo");
    }
}
