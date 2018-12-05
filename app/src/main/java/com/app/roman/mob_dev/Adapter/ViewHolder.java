package com.app.roman.mob_dev.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.roman.mob_dev.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tags)
    protected TextView name;
    @BindView(R.id.user)
    protected TextView user;
    @BindView(R.id.image)
    protected ImageView image;

    ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}