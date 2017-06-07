package com.happy.photoviewer.photoviewer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.happy.photoviewer.PhotoViewApp;
import com.happy.photoviewer.R;
import com.happy.photoviewer.entities.Photo;
import com.happy.photoviewer.photoviewer.PhotoViewerPresenter;
import com.happy.photoviewer.photoviewer.adapters.PhotoViewerFragmentStatePagerAdapter;
import com.happy.photoviewer.photoviewer.di.PhotoViewerComponent;
import com.happy.photoviewer.widget.CircleIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoViewerActivity extends AppCompatActivity implements PhotoViewerView{

    private static final String TAG = PhotoViewerActivity.class.getSimpleName();
    //public static final String KEY_LIST = "key_list";
    public static final String KEY_POSITION = "key_position";

    @BindView(R.id.image_container)
    ViewPager imageContainer;
    @BindView(R.id.view_indicator)
    CircleIndicatorView indicatorView;
   // private ArrayList<Parcelable> list = new ArrayList<Parcelable>();
    private int position = 0;
    private PhotoViewerPresenter presenter;
    private PhotoViewerFragmentStatePagerAdapter adapter;
    private PhotoViewerComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        ButterKnife.bind(this);

        setupInjection();
        setupIntent();
        setupViewPagerAdapter();

        presenter.onCreate();
        presenter.getPhotos();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        presenter.rxUnsubscribe();
        super.onStop();
    }

    private void setupInjection() {
        component = ((PhotoViewApp)getApplication()).getPhotoViewerComponent(this, this);
        presenter = getPresenter();
        adapter = getAdapter();
    }
    private void setupViewPagerAdapter() {
        imageContainer.setAdapter(adapter);
        imageContainer.setCurrentItem(position, true);
    }

    private void setupIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            position = intent.getExtras().getInt(KEY_POSITION);
        }

    }



    @Override
    public void showPhotos(List<Photo> photos) {
        Log.d(TAG, "test="+photos.size());
        adapter.setList(photos);
        adapter.notifyDataSetChanged();
        setupViewIndicator(photos.size());
    }


    @Override
    public void showPhotosLoadError() {
        Toast.makeText(this, "Photo list load error", Toast.LENGTH_SHORT).show();
    }
    private void setupViewIndicator(int viewCount) {
        indicatorView.createDotPanel(viewCount, R.drawable.selector_circle);
        indicatorView.select(position);
        imageContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                indicatorView.select(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        imageContainer.setCurrentItem(position);
    }

    public PhotoViewerFragmentStatePagerAdapter getAdapter() {
        return component.getAdapter();
    }

    public PhotoViewerPresenter getPresenter() {
        return  component.getPresenter();
    }



}
