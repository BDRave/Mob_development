package com.app.roman.mob_dev;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiAdapter {

    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Hero>> getHeroes();

}
