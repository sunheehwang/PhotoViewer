package com.happy.photoviewer.main.list_images;

import android.util.Log;
import com.happy.photoviewer.main.MainActivity;
import com.happy.photoviewer.main.network.APIService;
import com.happy.photoviewer.main.network.ImageModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListImagesPresenter implements ListImagesContract.Presenter {

  private static final String TAG = ListImagesPresenter.class.getSimpleName();
  private final ListImagesContract.View mView;

  public ListImagesPresenter(ListImagesContract.View view) {
    view.setPresenter(this);
    mView = view;
  }

  @Override public void start() {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://demo2587971.mockable.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    APIService apiService  = retrofit.create(APIService.class);
    Call<ImageModel> call = apiService.loadImage();

    call.enqueue(new Callback<ImageModel>() {
      @Override
      public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
        mView.showList(response.body().getList());
      }

      @Override public void onFailure(Call<ImageModel> call, Throwable t) {
       //Log.d(TAG, "result="+t.toString());
        mView.showListLoadError();
      }
    });
  }

  @Override public void clickPhotoListItem(int position) {
    mView.launchActivity(position);
  }
}
