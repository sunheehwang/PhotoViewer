package com.happy.photoviewer.photoviewer;

import com.happy.photoviewer.entities.Photo;
import com.happy.photoviewer.libs.db.PhotoListRepository;

import rx.Observable;

/**
 * Created by Sun on 2017-06-05.
 */

public class PhotoViewerInteractorImpl implements  PhotoViewerInteractor{
    private final PhotoListRepository repository;

    public PhotoViewerInteractorImpl(PhotoListRepository repository) {
        this.repository = repository;
    }
    @Override
    public Observable<Photo> getPhotos() {
        return this.repository.getPhotos();
    }
}
