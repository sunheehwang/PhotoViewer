package com.happy.photoviewer.libs.db;

import com.happy.photoviewer.api.APIService;
import com.happy.photoviewer.entities.Photo;
import com.happy.photoviewer.entities.Result;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Sun on 2017-06-04.
 */

public class PhotoListRepositoryImpl implements PhotoListRepository {

    private static final long STALE_MS = 20 * 1000 ;
    private final APIService service;
    private List<Photo> photos;
    private long timestamp;

    public PhotoListRepositoryImpl(APIService service) {
        this.service = service;
        this.timestamp = System.currentTimeMillis();
        photos = new ArrayList<Photo>();
    }

    private boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    private Observable<Photo> getPhotosFromMemory() {
        if (isUpToDate()) {
            return Observable.from(photos);
        } else {
            timestamp = System.currentTimeMillis();
            photos.clear();
            return Observable.empty();
        }
    }

    private Observable<Photo> getPhotosFromNetwork() {
        return service.getImages().concatMap(new Func1<Result, Observable<Photo>>() {
            @Override
            public Observable<Photo> call(Result result) {
                return Observable.from(result.getList());
            }
        }).doOnNext(new Action1<Photo>() {
            @Override
            public void call(Photo photo) {
                photos.add(photo);
            }
        });
    }
    @Override
    public Observable<Photo> getPhotos() {
        return getPhotosFromMemory().switchIfEmpty(getPhotosFromNetwork());
    }

}
