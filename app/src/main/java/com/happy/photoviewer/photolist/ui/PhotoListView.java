package com.happy.photoviewer.photolist.ui;

import com.happy.photoviewer.entities.Photo;

import java.util.List;

/**
 * Created by Sun on 2017-06-04.
 */

public interface PhotoListView {
    void showPhotos(List<Photo> list);
    void showPhotosLoadError();
}
