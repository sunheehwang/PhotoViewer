package com.happy.photoviewer.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.happy.photoviewer.R;
import com.happy.photoviewer.main.network.PhotoModel;
import com.happy.photoviewer.main.widget.CircleIndicatorView;
import java.util.ArrayList;

public class ImageViewerActivity extends AppCompatActivity {

  private static final String TAG = ImageViewerActivity.class.getSimpleName();
  public static final String KEY_LIST = "key_list";
  public static final String KEY_POSITION = "key_position";

  @BindView(R.id.image_container) ViewPager imageContainer;
  @BindView(R.id.veiw_indicator) CircleIndicatorView indicatorView;
  private ArrayList<Parcelable> list = new ArrayList<Parcelable>();
  private int position = 0;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_viewer);
    ButterKnife.bind(this);

    Intent intent = getIntent();
    if (intent != null) {
      list = intent.getExtras().getParcelableArrayList(KEY_LIST);
      Log.d(TAG, "result=" + list.size());
      position = intent.getExtras().getInt(KEY_POSITION);
    }

    imageContainer.setAdapter(new ViewPagerAdapter());
    //imageContainer.setAdapter(new ImageViewerFragmentPagerAdapter(getSupportFragmentManager()));
    //imageContainer.setAdapter(new ImageViewerFragmentStatePagerAdapter(getSupportFragmentManager()));

    imageContainer.setCurrentItem(position, true);
    indicatorView.createDotPanel(list.size(), R.drawable.selector_circle);
    indicatorView.select(position);
    imageContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      @Override public void onPageSelected(int position) {
        indicatorView.select(position);
      }

      @Override public void onPageScrollStateChanged(int state) {
      }
    });
  }

  private final class ImageViewerFragmentPagerAdapter extends FragmentPagerAdapter {
    public ImageViewerFragmentPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
      Fragment fragment = ImageViewerFragment.newInstance((PhotoModel) list.get(position));
      return fragment;
    }

    @Override public int getCount() {
      return list.size();
    }

  }

  private final class ImageViewerFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    public ImageViewerFragmentStatePagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
      Fragment fragment = ImageViewerFragment.newInstance((PhotoModel) list.get(position));
      return fragment;
    }

    @Override public int getCount() {
      return list.size();
    }
  }

  private final class ViewPagerAdapter extends PagerAdapter {

    @Override public int getCount() {
      return list.size();
    }

    @Override public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
      PhotoModel model = (PhotoModel) list.get(position);

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
      Glide.with(getApplicationContext()).load(model.getUrl()).centerCrop().into(imageView);
      container.addView(view);

      Log.d(TAG, "ViewPager = instantiate");
      return view;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View)object);
      Log.d(TAG, "ViewPager = destoryItem!!");
    }

    @Override public void destroyItem(View container, int position, Object object) {
      ((ViewGroup)container).removeView((View) object);
      Log.d(TAG, "ViewPager = destoryItem");
    }


  }

  static final class ViewHolder {
    @BindView(R.id.image) ImageView imageView;
    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.url) TextView urlView;
    @BindView(R.id.imageSize) TextView imageSizeView;
    @BindView(R.id.dateTaken) TextView dateTaken;

    ViewHolder(View v) {
      ButterKnife.bind(this, v);
    }
  }
}
