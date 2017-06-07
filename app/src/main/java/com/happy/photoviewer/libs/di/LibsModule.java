package com.happy.photoviewer.libs.di;

import android.app.Activity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.happy.photoviewer.api.APIClient;
import com.happy.photoviewer.api.APIService;
import com.happy.photoviewer.libs.base.GlideImageLoader;
import com.happy.photoviewer.libs.base.ImageLoader;
import com.happy.photoviewer.libs.db.PhotoListRepository;
import com.happy.photoviewer.libs.db.PhotoListRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sun on 2017-06-04.
 */
@Module
public class LibsModule {

    private final Activity activity;

    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager) {
        return new GlideImageLoader(requestManager);
    }

    @Provides @Singleton
    RequestManager providesRequestManager(Activity activity) {
        return Glide.with(activity);
    }

    @Provides @Singleton
    Activity providesActivity() {
        return this.activity;
    }

    @Provides @Singleton
    PhotoListRepository providesPhotoListRepository(APIService service) {
        return new PhotoListRepositoryImpl(service);
    }

    @Provides @Singleton
    APIService providesAPIService() {
        return new APIClient().getAPIService();
    }

}
