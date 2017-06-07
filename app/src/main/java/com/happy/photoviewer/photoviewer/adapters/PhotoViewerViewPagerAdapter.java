package com.happy.photoviewer.photoviewer.adapters;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happy.photoviewer.R;
import com.happy.photoviewer.entities.Photo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sun on 2017-06-07.
 */

public final class PhotoViewerViewPagerAdapter extends PagerAdapter {

    private static final String TAG = PhotoViewerViewPagerAdapter.class.getSimpleName() ;
    private List<Photo> list = null;

    public PhotoViewerViewPagerAdapter() {
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Photo model = (Photo) list.get(position);

        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = (ViewGroup) inflater.inflate(R.layout.image_viewer, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        TextView titleView = (TextView) view.findViewById(R.id.title);
        TextView urlView = (TextView) view.findViewById(R.id.url);
        TextView imageSizeView = (TextView) view.findViewById(R.id.imageSize);
        TextView dateTaken = (TextView) view.findViewById(R.id.dateTaken);
        titleView.setText(model.getTitle());
        imageSizeView.setText(model.getWidth() + "x" + model.getHeight());
        urlView.setText(model.getUrl());
        dateTaken.setText(model.getDateTaken());
        Glide.with(container.getContext()).load(model.getUrl()).centerCrop().into(imageView);
        container.addView(view);

        Log.d(TAG, "ViewPager = instantiate");
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Log.d(TAG, "ViewPager = destoryItem!!");
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewGroup) container).removeView((View) object);
        Log.d(TAG, "ViewPager = destoryItem");
    }

    public static final class ViewHolder {
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.title)
        TextView titleView;
        @BindView(R.id.url)
        TextView urlView;
        @BindView(R.id.imageSize)
        TextView imageSizeView;
        @BindView(R.id.dateTaken)
        TextView dateTaken;

        ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }

    }

}

