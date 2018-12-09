package com.app.roman.mob_dev.presenters;

import android.content.Context;

import com.app.roman.mob_dev.api.ApiClient;
import com.app.roman.mob_dev.contracts.MainContract;
import com.app.roman.mob_dev.model.DiskPhoto;
import com.app.roman.mob_dev.model.PhotoData;
import com.app.roman.mob_dev.model.PhotoResponse;
import com.app.roman.mob_dev.util.Constants;
import com.app.roman.mob_dev.util.PreferenceUtil;
import com.app.roman.mob_dev.util.ExecutorSupplier;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter, Callback<PhotoResponse> {

    private MainContract.View mView;

    public MainPresenter(MainContract.View view)
    {
        this.mView = view;
    }


    @Override
    public void getImages(String text, int page, int count) {
        getPhotos(text, page, count);
    }

    //Get offline images from cache
    @Override
    public void getOfflineImages(final Context mContext, String text) {
        mView.showProgress();
        final DiskPhoto diskPhoto = PreferenceUtil.getInstance(mContext).getDiskPhoto();

        if (diskPhoto!=null)
        {
            if (diskPhoto.getText().equals(text))
            {
                final List<String> urls = diskPhoto.getUrls();
                mView.displayOfflineImages(urls);
            }
            else
            {
                mView.showErrorMessage("Saved data not found.");
            }
        }
        else
        {
            mView.showErrorMessage("Some error happened;");
        }
    }

    @Override
    public void displayImage(int position) {
        mView.displayImage(position);
    }

    @Override
    public void onResponse(Call<PhotoResponse> call, Response<PhotoResponse> response) {
        mView.hideProgress();
        mView.hideKeyboard();
        PhotoData data = response.body().getData();
        if (data==null)
            mView.showErrorMessage("Some error occurred.");
        else
        if(data.getPage()==1)
            mView.displayImages(data.getPhotos());
        else
            mView.displayPagedImages(data.getPhotos());
    }

    @Override
    public void onFailure(Call<PhotoResponse> call, Throwable t) {
        mView.hideProgress();
        mView.hideKeyboard();
        mView.showErrorMessage("Some error occurred");
    }

    public void getPhotos(final String text, final int page, final int count)
    {
        if (page==1)
            mView.showProgress();

        ExecutorSupplier.getInstance().getWorkerThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Call<PhotoResponse> c  = ApiClient.getApiInterface().getPhotos(Constants.METHOD, Constants.API_KEY, text, page, Constants.EXTRAS, count, Constants.FORMAT, 1);
                c.enqueue(MainPresenter.this);
            }
        });
    }
}
