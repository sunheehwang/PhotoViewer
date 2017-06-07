package com.happy.photoviewer.photoviewer;

import com.happy.photoviewer.entities.Photo;
import com.happy.photoviewer.photolist.PhotoListPresenter;
import com.happy.photoviewer.photoviewer.ui.PhotoViewerView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Sun on 2017-06-05.
 */

public class PhotoViewerPresenterImpl implements PhotoViewerPresenter {

    private final PhotoViewerView view;
    private final PhotoViewerInteractor interactor;
    private Subscription subscription;
    private List<Photo> photos = new ArrayList<>();

    public PhotoViewerPresenterImpl(PhotoViewerView view, PhotoViewerInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void getPhotos() {
        photos.clear();
        subscription = interactor.getPhotos().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Photo>() {
            @Override
            public void onCompleted() {
                if (view != null) {
                    view.showPhotos(photos);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (view != null) {
                    view.showPhotosLoadError();
                }
            }

            @Override
            public void onNext(Photo photo) {
                photos.add(photo);
            }
        });
    }

    @Override
    public void rxUnsubscribe() {
        subscription.unsubscribe();
    }


}
