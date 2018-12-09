package com.app.roman.mob_dev.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.roman.mob_dev.model.DiskPhoto;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PreferenceUtil {

    private static PreferenceUtil preferenceUtils;
    private static SharedPreferences sharedPreferences;

    private PreferenceUtil() {

    }

    public static PreferenceUtil getInstance(Context context) {
        if (preferenceUtils == null) {
            preferenceUtils = new PreferenceUtil();
            sharedPreferences = getSharedPreferences(context);
        }
        return preferenceUtils;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(Constants.APP_PREFERENCE, Context.MODE_PRIVATE);
        }

        return sharedPreferences;
    }

    public void writePhotoObject(DiskPhoto photo)
    {
        Gson gson = new Gson();
        String json = gson.toJson(photo);
        sharedPreferences.edit().putString(Constants.DISK_PHOTO, json).apply();
    }

    public void saveText(String text) {
        Gson gson = new Gson();
        DiskPhoto photo = new DiskPhoto();
        photo.setText(text);
        photo.setUrls(new ArrayList<String>());
        String json = gson.toJson(photo);
        sharedPreferences.edit().putString(Constants.DISK_PHOTO, json).apply();
    }

    public void addUrl(String url)
    {
        String string = sharedPreferences.getString(Constants.DISK_PHOTO, null);
        Gson gson = new Gson();
        DiskPhoto photo = gson.fromJson(string, DiskPhoto.class);
        photo.getUrls().add(url);
        writePhotoObject(photo);
    }

    public DiskPhoto getDiskPhoto() {
        String string = sharedPreferences.getString(Constants.DISK_PHOTO, null);
        Gson gson = new Gson();
        return gson.fromJson(string, DiskPhoto.class);
    }
}
