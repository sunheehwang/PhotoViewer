package com.happy.photoviewer.photoviewer;

/**
 * Created by Sun on 2017-06-05.
 */

public interface PhotoViewerPresenter {

    void getPhotos();
    void rxUnsubscribe();

    void onCreate();
}
