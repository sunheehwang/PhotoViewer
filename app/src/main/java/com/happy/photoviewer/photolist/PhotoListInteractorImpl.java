package com.happy.photoviewer.photolist;

import com.happy.photoviewer.entities.Photo;
import com.happy.photoviewer.libs.db.PhotoListRepository;

import rx.Observable;


/**
 * Created by Sun on 2017-06-04.
 */

public class PhotoListInteractorImpl implements PhotoListInteractor{

    private final PhotoListRepository repository;

    public PhotoListInteractorImpl(PhotoListRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Photo> getPhotos() {
        return repository.getPhotos();
    }
}
