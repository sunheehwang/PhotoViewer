package com.happy.photoviewer.photoviewer.di;

import com.happy.photoviewer.libs.di.LibsModule;
import com.happy.photoviewer.photoviewer.PhotoViewerPresenter;
import com.happy.photoviewer.photoviewer.adapters.PhotoViewerFragmentStatePagerAdapter;
import com.happy.photoviewer.photoviewer.adapters.PhotoViewerViewPagerAdapter;
import com.happy.photoviewer.photoviewer.ui.PhotoViewerActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sun on 2017-06-08.
 */
@Singleton
@Component(modules = {PhotoViewerModule.class, LibsModule.class})
public interface PhotoViewerComponent {

    PhotoViewerPresenter getPresenter();
    PhotoViewerFragmentStatePagerAdapter getAdapter();

}
