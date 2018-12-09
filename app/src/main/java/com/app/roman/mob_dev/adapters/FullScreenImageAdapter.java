package com.app.roman.mob_dev.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.roman.mob_dev.R;
import com.app.roman.mob_dev.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FullScreenImageAdapter extends PagerAdapter {

    private List<Photo> mPhotos;
    private Context mContext;
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;

    public FullScreenImageAdapter(Activity activity, List<Photo> mPhotos, Context mContext) {
        this.mActivity = activity;
        this.mPhotos = mPhotos;
        this.mContext = mContext;

        mLayoutInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mPhotos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.image_layout_fullscreen, container, false);

        ImageView mImageView = (ImageView) itemView.findViewById(R.id.imageView);

        Picasso.with(mContext)
                .load(mPhotos.get(position).getUrl())
                .noFade()
                .into(mImageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
