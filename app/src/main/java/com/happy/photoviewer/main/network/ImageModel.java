package com.happy.photoviewer.main.network;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data public class ImageModel {
  @SerializedName("stat") private String stat;
  @SerializedName("page") private int page;
  @SerializedName("total_page") private int totalPage;
  @SerializedName("photos") private List<PhotoModel> list = new ArrayList<PhotoModel>();
}
