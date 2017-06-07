package com.happy.photoviewer.photoviewer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.happy.photoviewer.entities.Photo;
import com.happy.photoviewer.libs.base.ImageLoader;
import com.happy.photoviewer.photoviewer.ui.PhotoViewerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sun on 2017-06-07.
 */

public final class PhotoViewerFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private final ImageLoader imageLoader;
    private List<Photo> list = new ArrayList<>();

    public PhotoViewerFragmentStatePagerAdapter(FragmentManager fm, ImageLoader imageLoader) {
        super(fm);
        this.imageLoader = imageLoader;
    }

    public void setList(List<Photo> list) {
        this.list = list;
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

