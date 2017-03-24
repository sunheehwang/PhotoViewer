package com.happy.photoviewer.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class ImageViewerFragment extends Fragment {

  private static final String TAG = ImageViewerFragment.class.getSimpleName() ;
  private static final String KEY_MODEL = "key_model";

  @BindView(R.id.image) ImageView imageView;
  @BindView(R.id.title) TextView titleView;
  @BindView(R.id.url) TextView urlView;
  @BindView(R.id.imageSize) TextView imageSizeView;
  @BindView(R.id.dateTaken) TextView dateTaken;

  public static ImageViewerFragment newInstance(PhotoModel model) {
    ImageViewerFragment fragment = new ImageViewerFragment();
    Bundle args = new Bundle();
    args.putParcelable(KEY_MODEL, model);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    Log.d(TAG, "state = onAttach");
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "state = onCreate");
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    Log.d(TAG, "state = onCreateView");
    View view = inflater.inflate(R.layout.image_viewer, container, false);
    ButterKnife.bind(this, view);
    PhotoModel model = getArguments().getParcelable(KEY_MODEL);

    titleView.setText(model.getTitle());
    imageSizeView.setText(model.getWidth() + "x" + model.getHeight());
    urlView.setText(model.getUrl());
    dateTaken.setText(model.getDateTaken());
    Glide.with(container.getContext()).load(model.getUrl()).centerCrop().into(imageView);
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    Log.d(TAG, "state = onActivityCreated");
  }

  @Override public void onStart() {
    super.onStart();
    Log.d(TAG, "state = onStart");
  }

  @Override public void onResume() {
    super.onResume();
    Log.d(TAG, "state = onResume");
  }

  @Override public void onPause() {
    super.onPause();
    Log.d(TAG, "state = onPause");
  }

  @Override public void onStop() {
    super.onStop();
    Log.d(TAG, "state = onStop");
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    Log.d(TAG, "state = onDestroyView");
  }

  @Override public void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "state = onDestroy");
  }

  @Override public void onDetach() {
    super.onDetach();
    Log.d(TAG, "state = onDetach");
  }
}
