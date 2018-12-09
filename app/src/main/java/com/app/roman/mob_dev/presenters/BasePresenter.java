package com.app.roman.mob_dev.presenters;

import com.app.roman.mob_dev.views.BaseView;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}