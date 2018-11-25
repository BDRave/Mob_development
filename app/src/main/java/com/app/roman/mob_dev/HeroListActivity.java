package com.app.roman.mob_dev;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroListActivity extends AppCompatActivity {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heroes_list);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiAdapter.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiAdapter apiAdapter = retrofit.create(ApiAdapter.class);

        Call<List<Hero>> call = apiAdapter.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroes = response.body();

                String[] imageurl = new String[heroes.size()];

                for(Hero h: heroes){
                    Log.d("name",h.getName());
                    Log.d("realname",h.getRealname());
                    Log.d("bio",h.getBio());
                    Log.d("image",h.getImageurl());
                }
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText((getApplicationContext()),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

}
