package com.robertzhang.commonlibs.behavior.byeburgernavigationview;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;


public class ByeBurgerBottomBehavior extends ByeBurgerBehavior {

  private com.robertzhang.commonlibs.behavior.byeburgernavigationview.TranslateAnimateHelper mAnimateHelper;

  public ByeBurgerBottomBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }


  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dx, int dy, int[] consumed) {

    if (isFirstMove) {
      isFirstMove = false;
      mAnimateHelper = com.robertzhang.commonlibs.behavior.byeburgernavigationview.TranslateAnimateHelper.get(child);
      mAnimateHelper.setStartY(child.getY());
      mAnimateHelper.setMode(com.robertzhang.commonlibs.behavior.byeburgernavigationview.TranslateAnimateHelper.MODE_BOTTOM);
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
