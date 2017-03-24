package com.happy.photoviewer.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.happy.photoviewer.R;
import com.happy.photoviewer.main.list_images.ListImagesContract;
import com.happy.photoviewer.main.list_images.ListImagesPresenter;

import com.happy.photoviewer.main.network.PhotoModel;
import java.util.List;


public class MainActivity extends AppCompatActivity  implements ListImagesContract.View {

  private String TAG = MainActivity.class.getSimpleName();

  @BindView(R.id.recyclerView)
  RecyclerView mRecyclerView;

  private  RecyclerViewAdapter mPhotoViewAdapter;
  private ListImagesContract.Presenter mPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    Log.d(TAG, "state = onCreate");

    mPresenter = new ListImagesPresenter(this);

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    layoutManager.scrollToPosition(0);
    mRecyclerView.setLayoutManager(layoutManager);

    mPhotoViewAdapter = new RecyclerViewAdapter();
    mPhotoViewAdapter.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        int position = mRecyclerView.getChildLayoutPosition(v);
        mPresenter.clickPhotoListItem(position);
      }
    });
    mRecyclerView.setAdapter(mPhotoViewAdapter);

    mPresenter.start();
  }

 @Override protected void onStart() {
    super.onStart();
   Log.d(TAG, "state = onStart");
  }

  @Override protected void onResume() {
    super.onResume();
    Log.d(TAG, "state = onResume");
  }

  @Override protected void onPause() {
    super.onPause();
    Log.d(TAG, "state = onPause");
  }

  @Override protected void onStop() {
    super.onStop();
    Log.d(TAG, "state = onStop");
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "state = onDestroy");
  }

  @Override public void setPresenter(ListImagesContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override public void showList(List<PhotoModel> list) {
    mPhotoViewAdapter.setList(list);
    mPhotoViewAdapter.notifyDataSetChanged();
  }

  @Override public void showListLoadError() {
    Toast.makeText(this, "Photo list load error", Toast.LENGTH_SHORT).show();
  }

  @Override public void launchActivity(int position) {
    PhotoModel model = mPhotoViewAdapter.getItem(position);
    Toast.makeText(getApplicationContext(), model.getTitle(), Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(getApplicationContext(), ImageViewerActivity.class);
    intent.putParcelableArrayListExtra(ImageViewerActivity.KEY_LIST, mPhotoViewAdapter.getList());
    intent.putExtra(ImageViewerActivity.KEY_POSITION, position);
    startActivity(intent);
  }
}
