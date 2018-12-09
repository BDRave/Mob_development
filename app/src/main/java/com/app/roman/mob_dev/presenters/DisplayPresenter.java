package com.app.roman.mob_dev.presenters;

import com.app.roman.mob_dev.model.Photo;
import com.app.roman.mob_dev.contracts.DisplayContract;

import java.util.List;


public class DisplayPresenter extends BasePresenterImpl<DisplayContract.View> implements DisplayContract.Presenter {

    private DisplayContract.View mView;

    public DisplayPresenter(DisplayContract.View view)
    {
        this.mView = view;
    }

    @Override
    public void displayImage(List<Photo> photos, int position) {
        mView.displayImage(photos, position);
    }
}
