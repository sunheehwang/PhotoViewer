package com.happy.photoviewer.photolist;

import com.happy.photoviewer.entities.Photo;
import com.happy.photoviewer.photolist.ui.PhotoListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Sun on 2017-06-04.
 */

public class PhotoListPresenterImpl implements PhotoListPresenter{

    private final PhotoListInteractor interactor;
    private PhotoListView view;
    private Subscription subscription;
    private List<Photo> photos;


    public PhotoListPresenterImpl(PhotoListView view, PhotoListInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
        photos = new ArrayList<>();
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestory() {
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
