package com.happy.photoviewer.main.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.happy.photoviewer.R;

public class CircleIndicatorView extends LinearLayout {
  private static final String TAG = CircleIndicatorView.class.getSimpleName();

  private final Context mContext;
  private int mDefaultCircleResId;
  private int mSelectCircleResId;
  private ImageView[] imageDots;
  private int itemMargin = 10;

  public CircleIndicatorView(Context context) {
    super(context);
    mContext = context;
  }

  public CircleIndicatorView(Context context, AttributeSet attrs) {
    super(context, attrs);
    mContext = context;
  }

  public void createDotPanel(int count, int circleResId) {
    if (count <= 0) {
      return ;
    }

    Log.d(TAG, "count circle="+count);
    imageDots = new ImageView[count];

    for (int i = 0; i < count; i++) {
      imageDots[i] = new ImageView(mContext);
      LinearLayout.LayoutParams params =
          new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
      params.topMargin = itemMargin;
      params.bottomMargin = itemMargin;
      params.leftMargin = itemMargin;
      params.rightMargin = itemMargin;
      params.gravity = Gravity.CENTER;

      imageDots[i].setLayoutParams(params);
      imageDots[i].setImageResource(R.drawable.selector_circle);
      imageDots[i].setSelected(false);
      this.addView(imageDots[i]);
    }
    select(0);
  }

  public void select(int position) {
    for (int i = 0; i < imageDots.length; i++) {
      if (i == position) {
        imageDots[i].setSelected(true);
      } else {
        if(imageDots[i].isSelected() == true) {
          imageDots[i].setSelected(false);
        }
      }
    }
  }

  public void createDotPanel(int count, int defaultCircleResId, int selectCircleResId) {
    if (count <= 0) {
      return ;
    }
    mDefaultCircleResId = defaultCircleResId;
    mSelectCircleResId = selectCircleResId;

    imageDots = new ImageView[count];

    for (int i = 0; i < count; i++) {
      imageDots[i] = new ImageView(mContext);
      LinearLayout.LayoutParams params =
          new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
      params.topMargin = itemMargin;
      params.bottomMargin = itemMargin;
      params.leftMargin = itemMargin;
      params.rightMargin = itemMargin;
      params.gravity = Gravity.CENTER;

      imageDots[i].setLayoutParams(params);
      imageDots[i].setImageResource(mDefaultCircleResId);
      imageDots[i].setTag(imageDots[i].getId(), false);
      this.addView(imageDots[i]);
    }
    selectDot(0);
  }

  private void selectDot(int position) {
    for (int i = 0; i < imageDots.length; i++) {
      if (i == position) {
        imageDots[i].setImageResource(mSelectCircleResId);
        imageDots[i].setTag(imageDots[i].getId(), true);
      } else {
        if((boolean)imageDots[i].getTag(imageDots[i].getId()) == true) {
          imageDots[i].setImageResource(mDefaultCircleResId);
          imageDots[i].setTag(imageDots[i].getId(), false);
        }
      }
    }
  }
}
