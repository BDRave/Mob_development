package com.app.roman.mob_dev;

import com.app.roman.mob_dev.CustomListView.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiAdapter {
    @GET("marvel")
    Call <List <Hero>> getHeroes();
}