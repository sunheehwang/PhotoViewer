package com.happy.photoviewer.photolist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.happy.photoviewer.PhotoViewApp;
import com.happy.photoviewer.R;
import com.happy.photoviewer.photoviewer.ui.PhotoViewerActivity;
import com.happy.photoviewer.photolist.di.PhotoListComponent;
import com.happy.photoviewer.photolist.ui.adapters.OnItemClickListener;
import com.happy.photoviewer.photolist.ui.adapters.PhotosAdapter;
import com.happy.photoviewer.entities.Photo;
import com.happy.photoviewer.photolist.PhotoListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhotoListActivity extends AppCompatActivity implements PhotoListView, OnItemClickListener{

    private String TAG = PhotoListActivity.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //@Inject
    PhotosAdapter photoViewAdapter;

    //@Inject
    PhotoListPresenter presenter;
    private PhotoListComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "state = onCreate");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupInjection();
        setupRecyclerView();
        presenter.onCreate();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "state = onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "state = onStart");
        presenter.getPhotos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "state = onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "state = onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "state = onStop");
        presenter.rxUnsubscribe();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        super.onDestroy();
        Log.d(TAG, "state = onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "state = onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "state = onRestoreInstanceState");
    }

    private void setupInjection() {
        component = ((PhotoViewApp)getApplication()).getPhotoListComponent(this, this, this);
        //component.inject(this);

        presenter = getPresenter();
        photoViewAdapter = getAdapter();
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(photoViewAdapter);
    }

    @Override
    public void showPhotos(List<Photo> list) {
        photoViewAdapter.setList(list);
        photoViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPhotosLoadError() {
        Toast.makeText(this, "Photo list load error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int position) {
        //Photo photo = photoViewAdapter.getItem(position);
        //Toast.makeText(getApplicationContext(), photo.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), PhotoViewerActivity.class);
        //intent.putParcelableArrayListExtra(PhotoViewerActivity.KEY_LIST, photoViewAdapter.getList());
        intent.putExtra(PhotoViewerActivity.KEY_POSITION, position);
        startActivity(intent);
    }

    public PhotoListPresenter getPresenter() {
        return component.getPresenter();
    }

    public PhotosAdapter getAdapter() {
        return component.getAdapter();
    }


}
