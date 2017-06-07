package com.happy.photoviewer.photolist.di;

import com.happy.photoviewer.api.APIClient;
import com.happy.photoviewer.api.APIService;
import com.happy.photoviewer.libs.base.ImageLoader;
import com.happy.photoviewer.photolist.PhotoListInteractor;
import com.happy.photoviewer.photolist.PhotoListInteractorImpl;
import com.happy.photoviewer.photolist.PhotoListPresenter;
import com.happy.photoviewer.photolist.PhotoListPresenterImpl;
import com.happy.photoviewer.libs.db.PhotoListRepository;
import com.happy.photoviewer.libs.db.PhotoListRepositoryImpl;
import com.happy.photoviewer.photolist.ui.PhotoListView;
import com.happy.photoviewer.photolist.ui.adapters.OnItemClickListener;
import com.happy.photoviewer.photolist.ui.adapters.PhotosAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sun on 2017-06-04.
 */
@Module
public class PhotoListModule {
    private final PhotoListView view;
    private final OnItemClickListener itemClickListener;

    public PhotoListModule(PhotoListView view, OnItemClickListener itemClickListener){
        this.view = view;
        this.itemClickListener = itemClickListener;
    }
    @Provides @Singleton
    PhotoListView providesPhotoListView() {
        return this.view;
    }

    @Provides @Singleton
    PhotoListPresenter providesPhotoListPresenter(PhotoListView view, PhotoListInteractor interactor) {
        return new PhotoListPresenterImpl(view, interactor);
    }

    @Provides @Singleton
    PhotoListInteractor provicesPhotoListInterator(PhotoListRepository repository) {
        return new PhotoListInteractorImpl(repository);
    }

/*
    @Provides @Singleton
    PhotoListRepository providesPhotoListRepository(APIService apiService) {
        return new PhotoListRepositoryImpl(apiService);
    }

    @Provides @Singleton
    APIService providesAPIService() {
        return new APIClient().getAPIService();
    }
*/

    @Provides @Singleton
    PhotosAdapter providesPhotosAdapter(ImageLoader imageLoader, OnItemClickListener itemClickListener) {
        return new PhotosAdapter(imageLoader, itemClickListener);
    }

    @Provides @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.itemClickListener;
    }

}
