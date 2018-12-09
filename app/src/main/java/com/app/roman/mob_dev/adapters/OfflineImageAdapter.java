package com.app.roman.mob_dev.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.roman.mob_dev.MyApplication;
import com.app.roman.mob_dev.R;
import com.app.roman.mob_dev.presenters.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OfflineImageAdapter extends RecyclerView.Adapter<OfflineImageAdapter.ImageViewHolder> {

    private List<String> urls;
    private Context mContext;
    private MainPresenter mPresenter;
    private CardView.LayoutParams params;
    private MyApplication application;

    public OfflineImageAdapter(List<String> urls, Context mContext, Activity activity, int columns, int width, MainPresenter mPresenter) {
        this.urls = urls;
        this.mContext = mContext;
        this.mPresenter = mPresenter;
        this.application = (MyApplication) activity.getApplication();

        int widthToSet = width - 20*columns;

        params = new CardView.LayoutParams(widthToSet/columns, widthToSet/columns);
        params.setMargins(10,0,10,0);
    }

    @Override
    public OfflineImageAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(mContext).inflate(R.layout.image_layout, parent, false);
        return new OfflineImageAdapter.ImageViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(final OfflineImageAdapter.ImageViewHolder holder, final int position) {

        final String url = urls.get(position);
        final Bitmap bitmap = (Bitmap) application.cache.get(url);

        holder.imageCardView.setLayoutParams(params);
        holder.imageCardView.setPadding(10,10,10,10);

        holder.mImageView.setImageBitmap(bitmap);

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.displayImage(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        protected ImageView mImageView;
        @BindView(R.id.imageCardView)
        protected CardView imageCardView;

        public ImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

