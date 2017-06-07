package com.happy.photoviewer.photolist.di;

import com.happy.photoviewer.libs.base.ImageLoader;
import com.happy.photoviewer.libs.di.LibsModule;
import com.happy.photoviewer.photolist.PhotoListPresenter;
import com.happy.photoviewer.photolist.ui.PhotoListActivity;
import com.happy.photoviewer.photolist.ui.adapters.OnItemClickListener;
import com.happy.photoviewer.photolist.ui.adapters.PhotosAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sun on 2017-06-04.
 */
@Singleton
@Component(modules = {PhotoListModule.class, LibsModule.class})
public interface PhotoListComponent {

    //void inject(PhotoListActivity activity);
    PhotoListPresenter getPresenter();
    PhotosAdapter getAdapter();

}
