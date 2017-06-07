package com.happy.photoviewer.photolist;


import com.happy.photoviewer.BaseTest;
import com.happy.photoviewer.photolist.ui.PhotoListView;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

/**
 * Created by Sun on 2017-06-04.
 */

public class PhotoListPresenterImplTest extends BaseTest {
    @Mock
    private PhotoListView view;
    @Mock
    private PhotoListInteractor interactor;


    private PhotoListPresenterImpl presenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        presenter = new PhotoListPresenterImpl(view, interactor);
    }

    @Test
    public void testGetPhotos_shouldShowPhotosIfPhotosAreEmpty() {
//        when(interactor).thenReturn(null);
  //      presenter.getPhotos();
    }

}
