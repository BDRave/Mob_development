package com.app.roman.mob_dev.api;

import com.app.roman.mob_dev.model.PhotoResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("rest/")
    Call<PhotoResponse> getPhotos(@Query("method") String method,
                                  @Query("api_key") String apiKey,
                                  @Query("text") String text,
                                  @Query("page") int page,
                                  @Query("extras") String extras,
                                  @Query("per_page") int perPage,
                                  @Query("format") String format,
                                  @Query("nojsoncallback") int noJsonCallback);
}

