package com.happy.photoviewer.photolist;

import com.happy.photoviewer.entities.Photo;

import rx.Observable;


/**
 * Created by Sun on 2017-06-04.
 */

public interface PhotoListInteractor {
    Observable<Photo> getPhotos();
}
