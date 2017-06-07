package com.happy.photoviewer.photolist.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.happy.photoviewer.R;
import com.happy.photoviewer.entities.Photo;
import com.happy.photoviewer.libs.base.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosAdapter
        extends RecyclerView.Adapter<PhotosAdapter.ListItemViewHolder> {

    private final static String TAG = PhotosAdapter.class.getSimpleName();
    private List<Photo> list = Collections.EMPTY_LIST;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public PhotosAdapter(ImageLoader imageLoader, OnItemClickListener itemClickListener) {
        this.imageLoader = imageLoader;
        this.onItemClickListener = itemClickListener;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        Photo item = list.get(position);
        Log.d(TAG, "list " + position + " " + item.toString());

        holder.titleView.setText(item.getTitle());
        holder.imageSizeView.setText(item.getWidth() + "x" + item.getHeight());
        holder.urlView.setText(item.getUrl());
        holder.dateTaken.setText(item.getDateTaken().toString());

        imageLoader.load(holder.imageView, item.getUrl(), android.R.drawable.ic_menu_slideshow);
        holder.setOnItemClickListener(position, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Photo> list) {
        this.list = list;
    }

    public Photo getItem(int position) {
        return list.get(position);
    }

    public ArrayList<Photo> getList() {
        return (ArrayList<Photo>) list;
    }

    final static class ListItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.title)
        TextView titleView;
        @BindView(R.id.url)
        TextView urlView;
        @BindView(R.id.imageSize)
        TextView imageSizeView;
        @BindView(R.id.dateTaken)
        TextView dateTaken;
        View view;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }

        public void setOnItemClickListener(final int position, final OnItemClickListener onItemClickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
    }
}
