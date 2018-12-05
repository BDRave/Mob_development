package com.app.roman.mob_dev;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient extends Application {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://simplifiedcoding.net/demos/";

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiAdapter getApiService() {
        return getRetrofitInstance().create(ApiAdapter.class);
    }
}