package com.app.roman.mob_dev;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.roman.mob_dev.Adapter.HeroAdapter;
import com.app.roman.mob_dev.CustomListView.Hero;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    public String name;
    @BindView(R.id.list_photos)
    protected RecyclerView recyclerView;
    public TextView noData;
    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeContainer;
    private HeroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeCall();
                swipeContainer.setRefreshing(false);
            }
        });
        makeCall();
    }

    public void makeCall() {
        Call<List<Hero>> call = RetrofitClient.getApiService().getHeroes();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call <List<Hero>> call, Response<List<Hero>> response) {
                Toast.makeText(MainActivity.this, R.string.successful_response,
                        Toast.LENGTH_LONG).show();
                if (response.body() == null) {
                    noData.setText("No Data");
                } else {
                    List<Hero> hits = response.body();
                    setAdapter(hits);
                }
            }

            @Override
            public void onFailure(Call <List<Hero>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, R.string.unsuccessful_response
                        + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }..f
        });
    }

    public void setAdapter(List <Hero> data) {
        adapter = HeroAdapter.getHeroAdapter();
        adapter.loadData(data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}