package com.happy.photoviewer.photoviewer.ui;

import com.happy.photoviewer.entities.Photo;

import java.util.List;

/**
 * Created by Sun on 2017-06-05.
 */

public interface PhotoViewerView {

    void showPhotos(List<Photo> photos);

    void showPhotosLoadError();
}
