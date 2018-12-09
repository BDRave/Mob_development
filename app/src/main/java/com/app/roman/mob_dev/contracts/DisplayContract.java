package com.app.roman.mob_dev.contracts;

import com.app.roman.mob_dev.model.Photo;
import com.app.roman.mob_dev.presenters.BasePresenter;
import com.app.roman.mob_dev.views.BaseView;

import java.util.List;


public class DisplayContract {

    public interface View extends BaseView {
        void displayImage(List<Photo> photos, int position);
    }

    public interface Presenter extends BasePresenter<DisplayContract.View> {
        void displayImage(List<Photo> photos, int position);
    }
}
