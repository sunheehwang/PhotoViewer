package com.happy.photoviewer.main.network;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class PhotoModel implements Parcelable{

  @SerializedName("title") private String title;
  @SerializedName("width") private int width;
  @SerializedName("height") private int height;
  @SerializedName("url") private String url;
  @SerializedName("date_taken") private String dateTaken;

  protected PhotoModel(Parcel in) {
    title = in.readString();
    width = in.readInt();
    height = in.readInt();
    url = in.readString();
    dateTaken = in.readString();
  }

  public static final Creator<PhotoModel> CREATOR = new Creator<PhotoModel>() {
    @Override public PhotoModel createFromParcel(Parcel in) {
      return new PhotoModel(in);
    }

    @Override public PhotoModel[] newArray(int size) {
      return new PhotoModel[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeInt(width);
    dest.writeInt(height);
    dest.writeString(url);
    dest.writeString(dateTaken);
  }
}
