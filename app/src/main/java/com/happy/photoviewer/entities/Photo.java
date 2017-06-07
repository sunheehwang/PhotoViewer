package com.happy.photoviewer.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Photo implements Parcelable{

  @SerializedName("title") private String title;
  @SerializedName("width") private int width;
  @SerializedName("height") private int height;
  @SerializedName("url") private String url;
  @SerializedName("date_taken") private String dateTaken;

  protected Photo(Parcel in) {
    title = in.readString();
    width = in.readInt();
    height = in.readInt();
    url = in.readString();
    dateTaken = in.readString();
  }

  public static final Creator<Photo> CREATOR = new Creator<Photo>() {
    @Override public Photo createFromParcel(Parcel in) {
      return new Photo(in);
    }

    @Override public Photo[] newArray(int size) {
      return new Photo[size];
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
