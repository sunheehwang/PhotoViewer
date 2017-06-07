package com.happy.photoviewer.libs.base;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.happy.photoviewer.libs.base.ImageLoader;

/**
 * Created by Sun on 2017-06-04.
 */

public class GlideImageLoader implements ImageLoader {

    private final RequestManager glideReqeustMananger;
    private RequestListener onFinshedImageLoadingListener;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideReqeustMananger = glideRequestManager;
    }

    @Override
    public void load(ImageView imageView, String URL, int placeHolderRes) {
        if (onFinshedImageLoadingListener != null) {
            glideReqeustMananger
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .listener(onFinshedImageLoadingListener)
                    .placeholder(placeHolderRes)
                    .into(imageView);
        } else {
            glideReqeustMananger
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(placeHolderRes)
                    .into(imageView);
        }
    }

    @Override
    public void load(ImageView imageView, String URL) {
        if (onFinshedImageLoadingListener != null) {
            glideReqeustMananger
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .listener(onFinshedImageLoadingListener)
                    .into(imageView);
        } else {
            glideReqeustMananger
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imageView);
        }
    }

    @Override
    public void setOnFinishedImageLoadingListener(Object listener) {
        if (listener instanceof RequestListener) {
            this.onFinshedImageLoadingListener = (RequestListener) listener;
        }
    }
}
