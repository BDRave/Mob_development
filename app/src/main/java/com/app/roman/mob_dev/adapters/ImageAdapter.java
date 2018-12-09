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
import android.widget.Toast;

import com.app.roman.mob_dev.MyApplication;
import com.app.roman.mob_dev.R;
import com.app.roman.mob_dev.model.Photo;
import com.app.roman.mob_dev.presenters.MainPresenter;
import com.app.roman.mob_dev.util.PreferenceUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<Photo> mPhotos;
    private Context mContext;
    private MainPresenter mPresenter;
    private CardView.LayoutParams params;
    private MyApplication application;

    public ImageAdapter(List<Photo> mPhotos, Context mContext, Activity activity, int columns, int width, MainPresenter mPresenter) {
        this.mPhotos = mPhotos;
        this.mContext = mContext;
        this.mPresenter = mPresenter;
        this.application = (MyApplication) activity.getApplication();

        int widthToSet = width - 20*columns;

        params = new CardView.LayoutParams(widthToSet/columns, widthToSet/columns);
        params.setMargins(10,0,10,0);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(mContext).inflate(R.layout.image_layout, parent, false);
        return new ImageViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {

        final Photo photo = mPhotos.get(position);

        holder.imageCardView.setLayoutParams(params);
        holder.imageCardView.setPadding(10,10,10,10);

        Picasso.with(mContext).load(photo.getUrl()).fit().centerCrop().into(holder.mImageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.mImageView.buildDrawingCache();
                Bitmap bitmap = holder.mImageView.getDrawingCache();
                application.cache.put(photo.getUrl(), bitmap);
                Toast.makeText(mContext, String.valueOf(application.cache.getSize()), Toast.LENGTH_SHORT).show();
                PreferenceUtil.getInstance(mContext).addUrl(photo.getUrl());
            }

            @Override
            public void onError() {

            }
        });
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.displayImage(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
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
