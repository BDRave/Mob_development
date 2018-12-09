package com.app.roman.mob_dev.contracts;

import android.content.Context;

import com.app.roman.mob_dev.model.Photo;
import com.app.roman.mob_dev.presenters.BasePresenter;
import com.app.roman.mob_dev.views.BaseView;

import java.util.List;

public class MainContract {

    public interface View extends BaseView {
        void displayImages(List<Photo> photos);
        void displayPagedImages(List<Photo> photos);
        void displayOfflineImages(List<String> photoUrls);
        void showErrorMessage(String message);
        void changeColumns(int columns);
        void displayImage(int position);
        void hideKeyboard();
    }

    public interface Presenter extends BasePresenter<View> {
        void getImages(String text, int page, int count);
        void getOfflineImages(Context context, String text);
        void displayImage(int position);
    }
}
