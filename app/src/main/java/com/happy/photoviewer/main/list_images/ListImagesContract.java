package com.happy.photoviewer.main.list_images;

import com.happy.photoviewer.main.network.PhotoModel;
import com.happy.photoviewer.main.util.BasePresenter;
import com.happy.photoviewer.main.util.BaseView;
import java.util.List;

public interface ListImagesContract {

  interface Presenter extends BasePresenter {

    void clickPhotoListItem(int position);
  }

  interface View extends BaseView<Presenter> {

    void showList(List<PhotoModel> list);

    void showListLoadError();

    void launchActivity(int position);
  }

}
