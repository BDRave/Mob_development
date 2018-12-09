package com.app.roman.mob_dev.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    private static ApiInterface apiInterface;

    private ApiClient() {
    }

    public static final String BASE_URL = "https://api.flickr.com/services/";

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getApiInterface() {
        return getClient().create(ApiInterface.class);
    }
}

