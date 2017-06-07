package com.happy.photoviewer.libs.base;

import android.widget.ImageView;

/**
 * Created by Sun on 2017-06-04.
 */

public interface ImageLoader {
    void load(ImageView imageView, String URL, int placeHolder);

    void load(ImageView imageView, String URL);

    void setOnFinishedImageLoadingListener(Object listener);
}
