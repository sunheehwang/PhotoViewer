package com.happy.photoviewer;

import android.app.Application;

import com.happy.photoviewer.libs.di.LibsModule;
import com.happy.photoviewer.photolist.di.DaggerPhotoListComponent;
import com.happy.photoviewer.photolist.di.PhotoListComponent;
import com.happy.photoviewer.photolist.di.PhotoListModule;
import com.happy.photoviewer.photolist.ui.PhotoListActivity;
import com.happy.photoviewer.photolist.ui.PhotoListView;
import com.happy.photoviewer.photolist.ui.adapters.OnItemClickListener;
import com.happy.photoviewer.photoviewer.di.DaggerPhotoViewerComponent;
import com.happy.photoviewer.photoviewer.di.PhotoViewerComponent;
import com.happy.photoviewer.photoviewer.di.PhotoViewerModule;
import com.happy.photoviewer.photoviewer.ui.PhotoViewerActivity;
import com.happy.photoviewer.photoviewer.ui.PhotoViewerView;

/**
 * Created by Sun on 2017-06-04.
 */

public class PhotoViewApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public PhotoListComponent getPhotoListComponent(PhotoListActivity activity, PhotoListView view, OnItemClickListener itemClickListener) {
        return DaggerPhotoListComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .photoListModule(new PhotoListModule(view, itemClickListener))
                .build();
    }

    public PhotoViewerComponent getPhotoViewerComponent(PhotoViewerActivity activity, PhotoViewerView view) {

        return DaggerPhotoViewerComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .photoViewerModule(new PhotoViewerModule(view))
                .build();
    }
}
