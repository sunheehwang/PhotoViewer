package com.happy.photoviewer.photoviewer.adapters;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.happy.photoviewer.entities.Photo;
import com.happy.photoviewer.libs.base.ImageLoader;
import com.happy.photoviewer.photoviewer.PhotoViewerPresenter;
import com.happy.photoviewer.photoviewer.ui.PhotoViewerFragment;

import java.util.List;

/**
 * Created by Sun on 2017-06-07.
 */

public final class PhotoViewerFragmentPagerAdapter extends FragmentPagerAdapter {
    private PhotoViewerPresenter presenter;

    List<Photo> list;
    ImageLoader imageLoader;
    public PhotoViewerFragmentPagerAdapter(FragmentManager fm, ImageLoader imageLoader) {
        super(fm);
        this.imageLoader = imageLoader;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = PhotoViewerFragment.newInstance((Photo) list.get(position), imageLoader);
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

}