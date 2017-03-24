package com.happy.photoviewer.main.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
  @GET("/images") Call<ImageModel> loadImage();
}
