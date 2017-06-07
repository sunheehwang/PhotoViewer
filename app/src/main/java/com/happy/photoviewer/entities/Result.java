package com.happy.photoviewer.entities;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data public class Result {
  @SerializedName("stat") private String stat;
  @SerializedName("page") private int page;
  @SerializedName("total_page") private int totalPage;
  @SerializedName("photos") private List<Photo> list = new ArrayList<Photo>();
}
