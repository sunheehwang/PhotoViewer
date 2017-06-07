package com.happy.photoviewer.libs.db;

import com.happy.photoviewer.entities.Photo;

import rx.Observable;

/**
 * Created by Sun on 2017-06-04.
 */

public interface PhotoListRepository {
    Observable<Photo> getPhotos();
}
