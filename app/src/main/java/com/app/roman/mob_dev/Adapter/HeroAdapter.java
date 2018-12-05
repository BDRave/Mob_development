package com.app.roman.mob_dev.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.roman.mob_dev.CustomListView.Hero;
import com.app.roman.mob_dev.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter <ViewHolder>  {
    private ArrayList <Hero> heroes = new ArrayList <>();
    private static HeroAdapter heroAdapter = null;

    public static HeroAdapter getHeroAdapter() {
        if (heroAdapter == null) {
            heroAdapter = new HeroAdapter();
        }
        return heroAdapter;
    }

    HeroAdapter() {
    }

    public void loadData (List<Hero> heroes){
        int position = getItemCount();
        this.heroes.addAll(heroes);
        notifyItemRangeInserted(position, this.heroes.size());
    }

    @Override
    public final ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public final void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Hero hero = heroes.get(position);
        Picasso.get().load(hero.getImageurl()).into(holder.image);
        holder.name.setText(hero.getName());
        holder.user.setText(hero.getRealname());
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }
}
