package com.happy.photoviewer.main;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.happy.photoviewer.R;
import com.happy.photoviewer.main.network.PhotoModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter
    extends RecyclerView.Adapter<RecyclerViewAdapter.ListItemViewHolder> {

  private final static String TAG = RecyclerViewAdapter.class.getSimpleName();
  private List<PhotoModel> list = Collections.EMPTY_LIST;
  private View.OnClickListener mOnClickListener;

  public void setOnClickListener (View.OnClickListener listener) {
    mOnClickListener = listener;
  }
  @Override public ListItemViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
    view.setOnClickListener(mOnClickListener);
    return new ListItemViewHolder(view);
  }

  @Override public void onBindViewHolder(ListItemViewHolder holder, int position) {
    PhotoModel item = list.get(position);
    Log.d(TAG, "list " + position + " " + item.toString());

    holder.titleView.setText(item.getTitle());
    holder.imageSizeView.setText(item.getWidth() + "x" + item.getHeight());
    holder.urlView.setText(item.getUrl());
    holder.dateTaken.setText(item.getDateTaken().toString());

    Glide.with(holder.imageView.getContext())
        .load(item.getUrl())
        .placeholder(android.R.drawable.ic_menu_slideshow)
        .centerCrop()
        .into(holder.imageView);
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public void setList(List<PhotoModel> list) {
    this.list = list;
  }

  public PhotoModel getItem(int position) {
    return list.get(position);
  }

  public ArrayList<PhotoModel> getList() {
    return (ArrayList<PhotoModel>)list;
  }

  final static class ListItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image) ImageView imageView;
    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.url) TextView urlView;
    @BindView(R.id.imageSize) TextView imageSizeView;
    @BindView(R.id.dateTaken) TextView dateTaken;

    public ListItemViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
