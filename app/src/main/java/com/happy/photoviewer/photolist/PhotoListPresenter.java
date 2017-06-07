package com.happy.photoviewer.photolist;

/**
 * Created by Sun on 2017-06-04.
 */

public interface PhotoListPresenter {
    void onCreate();
    void onDestory();

    void getPhotos();

    void rxUnsubscribe();
}
