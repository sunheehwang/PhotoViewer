package com.happy.photoviewer.photoviewer;

import com.happy.photoviewer.entities.Photo;

import rx.Observable;

/**
 * Created by Sun on 2017-06-05.
 */

public interface PhotoViewerInteractor {
    Observable<Photo> getPhotos();
}
