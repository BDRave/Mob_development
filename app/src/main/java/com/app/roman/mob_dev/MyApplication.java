package com.app.roman.mob_dev;

import android.app.Application;
import android.graphics.Bitmap;

import com.app.roman.mob_dev.util.LruCache;


public class MyApplication extends Application {

    public LruCache cache;

    public void onCreate() {
        super.onCreate();
        cache = new LruCache<String, Bitmap>(30);
    }

    @Override
    public void onLowMemory() {
        cache.clear();
        super.onLowMemory();
    }
}
