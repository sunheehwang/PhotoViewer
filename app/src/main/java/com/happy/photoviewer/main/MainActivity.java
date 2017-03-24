package com.happy.photoviewer.main;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.happy.photoviewer.main.network.APIService;

import com.happy.photoviewer.main.network.ImageModel;
import com.happy.photoviewer.main.network.PhotoModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.common.base.Preconditions.checkNotNull;


public class MainActivity extends AppCompatActivity {

  private String TAG = MainActivity.class.getSimpleName();

  @BindView(R.id.recyclerView)
  RecyclerView mRecyclerView;

  private  RecyclerViewAdapter mPhotoViewAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    Log.d(TAG, "state = onCreate");


    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    layoutManager.scrollToPosition(0);
    mRecyclerView.setLayoutManager(layoutManager);

    mPhotoViewAdapter = new RecyclerViewAdapter();
    mPhotoViewAdapter.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //mPresenter.startActivity();
        int position = mRecyclerView.getChildLayoutPosition(v);
        PhotoModel model = mPhotoViewAdapter.getItem(position);
        Toast.makeText(getApplicationContext(), model.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ImageViewerActivity.class);
        intent.putParcelableArrayListExtra(ImageViewerActivity.KEY_LIST, mPhotoViewAdapter.getList());
        intent.putExtra(ImageViewerActivity.KEY_POSITION, position);
        startActivity(intent);
      }
    });
    mRecyclerView.setAdapter(mPhotoViewAdapter);

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://demo2587971.mockable.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    APIService apiService  = retrofit.create(APIService.class);
    Call<ImageModel> call = apiService.loadImage();

    call.enqueue(new Callback<ImageModel>() {
      @Override
      public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
       // Log.d(TAG, "result="+response.body().toString());
        mPhotoViewAdapter.setList(response.body().getList());
        mPhotoViewAdapter.notifyDataSetChanged();
      }

      @Override public void onFailure(Call<ImageModel> call, Throwable t) {
        Log.d(TAG, "result="+t.toString());
      }
    });


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

}
