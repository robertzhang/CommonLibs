package com.robertzhang.commonlibs.behavior.byeburgernavigationview;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;


public class ByeBurgerFloatButtonBehavior extends ByeBurgerBehavior {

  private com.robertzhang.commonlibs.behavior.byeburgernavigationview.ScaleAnimateHelper mAnimateHelper;

  public ByeBurgerFloatButtonBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);

  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dx, int dy, int[] consumed) {

    if (isFirstMove) {
      isFirstMove = false;
      mAnimateHelper = com.robertzhang.commonlibs.behavior.byeburgernavigationview.ScaleAnimateHelper.get(child);
    }
    if (Math.abs(dy) > mTouchSlop) {
      if (dy < 0) {
        if (mAnimateHelper.getState() == com.robertzhang.commonlibs.behavior.byeburgernavigationview.TranslateAnimateHelper.STATE_HIDE) {
          mAnimateHelper.show();
        }
      } else if (dy > 0) {
        if (mAnimateHelper.getState() == com.robertzhang.commonlibs.behavior.byeburgernavigationview.TranslateAnimateHelper.STATE_SHOW) {
          mAnimateHelper.hide();
        }
      }
    }
  }
}
