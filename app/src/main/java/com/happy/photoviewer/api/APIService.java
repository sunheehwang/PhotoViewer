package com.happy.photoviewer.api;

import com.happy.photoviewer.entities.Result;


import retrofit2.http.GET;
import rx.Observable;

public interface APIService {
  @GET("/images")
  Observable<Result> getImages();
}
