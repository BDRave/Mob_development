package com.app.roman.mob_dev.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.app.roman.mob_dev.R;
import com.app.roman.mob_dev.adapters.ImageAdapter;
import com.app.roman.mob_dev.adapters.OfflineImageAdapter;
import com.app.roman.mob_dev.contracts.MainContract;
import com.app.roman.mob_dev.model.Photo;
import com.app.roman.mob_dev.presenters.MainPresenter;
import com.app.roman.mob_dev.util.CommonLib;
import com.app.roman.mob_dev.util.Constants;
import com.app.roman.mob_dev.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    @BindView(R.id.searchEditText)
    protected EditText mEditText;
    @BindView(R.id.imageRecyclerView)
    RecyclerView imageRecyclerView;
    private ImageAdapter imageAdapter;
    private OfflineImageAdapter offlineImageAdapter;
    private int page = 1;
    private String text;
    private List<Photo> mPhotos;
    private int width;
    private int columns=2;
    private int count=10;
    private Context mContext;

    @NonNull
    @Override
    protected MainContract.Presenter getPresenterInstance() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        initViews();

        if(isNetworkAvailable())
        {
            initRecyclerView();
        }
        else
        {
            showShortToast("Please enable network.");
        }
    }


    private void initViews()
    {
        setSupportActionBar(mToolbar);
        width = getWindowManager().getDefaultDisplay().getWidth();
    }


    private void initRecyclerView()
    {
        imageRecyclerView.setLayoutManager(getLayoutManager(columns));

        imageRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    getImages(page);
                }
            }
        });
    }


    private boolean isNetworkAvailable()
    {
        return CommonLib.getNetworkState(mContext);
    }


    private GridLayoutManager getLayoutManager(int columns){

        return new GridLayoutManager(this, columns);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                page = 1;
                if(isNetworkAvailable())
                    getImages(page);
                else
                    mPresenter.getOfflineImages(mContext, text);
                break;
            case R.id.grid_column_2:
                columns = 2;
                changeColumns(2);
                break;
            case R.id.grid_column_3:
                columns = 3;
                changeColumns(3);
                break;
            case R.id.grid_column_4:
                columns = 4;
                changeColumns(4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getImages(int page)
    {
        text = mEditText.getText().toString();

        if(text.isEmpty() || text.equals(null))
            showShortToast("Please enter search text.");
        else
            count = columns*5;
        PreferenceUtil.getInstance(mContext).saveText(text);
        mPresenter.getImages(text, page, count);
    }


    @Override
    public void displayImages(List<Photo> photos) {
        page++;
        mPhotos = new ArrayList<Photo>();
        mPhotos.addAll(photos);
        imageAdapter = new ImageAdapter(mPhotos, mContext, this, columns, width, new MainPresenter(this));
        imageRecyclerView.setAdapter(imageAdapter);
    }


    @Override
    public void displayPagedImages(List<Photo> photos) {
        page++;
        mPhotos.addAll(photos);
        imageAdapter.notifyDataSetChanged();
    }


    @Override
    public void displayOfflineImages(List<String> urls) {

        offlineImageAdapter = new OfflineImageAdapter(urls, mContext, this, columns, width, new MainPresenter(this));
        imageRecyclerView.setAdapter(offlineImageAdapter);
    }


    @Override
    public void changeColumns(int columns) {
        imageRecyclerView.setLayoutManager(getLayoutManager(columns));
        imageAdapter = new ImageAdapter(mPhotos, mContext, this, columns, width, new MainPresenter(this));
        imageRecyclerView.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();
    }


    @Override
    public void displayImage(int position) {
        Intent intent = new Intent(this, DisplayActivity.class);

        intent.putParcelableArrayListExtra(Constants.IMAGES, new ArrayList<Parcelable>(mPhotos));
        intent.putExtra(Constants.POSITION, position);

        startActivityForResult(intent, Constants.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constants.REQUEST_CODE)
        {
            if (resultCode == Constants.RESULT_CODE)
            {
                int position = data.getIntExtra(Constants.POSITION, 0);
                imageRecyclerView.scrollToPosition(position);
            }
        }
    }

    @Override
    public void hideKeyboard() {
        CommonLib.hideKeyboard(this);
    }

    @Override
    public void showErrorMessage(String message) {
        showShortToast(message);
    }
}
