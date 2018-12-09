package com.app.roman.mob_dev.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.app.roman.mob_dev.R;
import com.app.roman.mob_dev.adapters.FullScreenImageAdapter;
import com.app.roman.mob_dev.contracts.DisplayContract;
import com.app.roman.mob_dev.model.Photo;
import com.app.roman.mob_dev.presenters.DisplayPresenter;
import com.app.roman.mob_dev.util.Constants;

import java.util.List;

import butterknife.BindView;


public class DisplayActivity extends BaseActivity<DisplayContract.Presenter> implements DisplayContract.View {

    @BindView(R.id.pager)
    protected ViewPager mViewPager;
    private FullScreenImageAdapter mFullscreenImageAdapter;
    private Context mContext;

    @NonNull
    @Override
    protected DisplayContract.Presenter getPresenterInstance() {
        return new DisplayPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mContext = getApplicationContext();

        supportPostponeEnterTransition();

        mContext = getApplicationContext();

        Bundle bundle = getIntent().getExtras();
        List<Photo> photos = bundle.getParcelableArrayList(Constants.IMAGES);
        int position = getIntent().getIntExtra(Constants.POSITION, 0);

        mPresenter.displayImage(photos, position);
    }

    @Override
    public void displayImage(List<Photo> photos, int position) {
        mFullscreenImageAdapter = new FullScreenImageAdapter(this,
                photos, mContext);

        mViewPager.setAdapter(mFullscreenImageAdapter);

        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(Constants.POSITION, mViewPager.getCurrentItem());
        setResult(Constants.RESULT_CODE, intent);
        finish();
    }
}
