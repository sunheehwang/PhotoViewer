package com.happy.photoviewer.photoviewer.di;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.happy.photoviewer.libs.base.ImageLoader;
import com.happy.photoviewer.libs.db.PhotoListRepository;
import com.happy.photoviewer.photoviewer.PhotoViewerInteractor;
import com.happy.photoviewer.photoviewer.PhotoViewerInteractorImpl;
import com.happy.photoviewer.photoviewer.PhotoViewerPresenter;
import com.happy.photoviewer.photoviewer.PhotoViewerPresenterImpl;
import com.happy.photoviewer.photoviewer.adapters.PhotoViewerFragmentStatePagerAdapter;
import com.happy.photoviewer.photoviewer.ui.PhotoViewerActivity;
import com.happy.photoviewer.photoviewer.ui.PhotoViewerView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sun on 2017-06-08.
 */

@Module
public class PhotoViewerModule {

    private final PhotoViewerView view;

    public PhotoViewerModule(PhotoViewerView view) {
        this.view = view;
    }

    @Provides @Singleton
    PhotoViewerPresenter providesPhotoViewerPresenterImpl(PhotoViewerView view, PhotoViewerInteractor interactor) {
        return new PhotoViewerPresenterImpl(view, interactor);
    }

    @Provides @Singleton
    PhotoViewerView providesPhotoViewerView() {
        return this.view;
    }

    @Provides @Singleton
    PhotoViewerInteractor providesPhotoViewerInteractor(PhotoListRepository repository) {
        return new PhotoViewerInteractorImpl(repository);
    }

    @Provides @Singleton
    PhotoViewerFragmentStatePagerAdapter providesPhotoViewerFragmentStatePagerAdapter(FragmentManager fm, ImageLoader imageLoader) {
        return new PhotoViewerFragmentStatePagerAdapter(fm, imageLoader);
    }

    @Provides @Singleton
    FragmentManager providesFragmentManager(Activity activity) {
        return ((PhotoViewerActivity)activity).getSupportFragmentManager();
    }
}
