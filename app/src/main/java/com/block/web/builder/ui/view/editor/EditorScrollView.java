/*
 *  This file is part of BlockWeb Builder.
 *
 *  BlockWeb Builder is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BlockWeb Builder is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with BlockWeb Builder.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.block.web.builder.ui.view.editor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.block.web.builder.utils.Utils;

public class EditorScrollView extends FrameLayout {
  private float x;
  private float y;
  private float initialX;
  private float initialY;
  private float initialXTE;
  private float initialYTE;
  private float validScroll = 0;
  private boolean useScroll = true;
  private boolean isScrollValueAchieved = false;

  public EditorScrollView(final Context context) {
    super(context);
    validScroll = ViewConfiguration.get(context).getScaledTouchSlop();
    initialXTE = -1.0f;
    initialYTE = -1.0f;
    useScroll = true;
    isScrollValueAchieved = false;
    initialX = 0.0f;
    initialY = 0.0f;
  }

  public EditorScrollView(final Context context, final AttributeSet set) {
    super(context, set);
    validScroll = ViewConfiguration.get(context).getScaledTouchSlop();
    initialXTE = -1.0f;
    initialYTE = -1.0f;
    useScroll = true;
    isScrollValueAchieved = false;
    initialX = 0.0f;
    initialY = 0.0f;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent motion) {
    if (!getEnableScroll()) {
      if (getScaleY() == 0) {
        return false;
      }
    }
    if (!useScroll) {
      return false;
    }
    int action = motion.getAction();

    if (action == MotionEvent.ACTION_DOWN) {
      initialX = motion.getX();
      initialY = motion.getY();
    }
    if (action == MotionEvent.ACTION_UP) {
      isScrollValueAchieved = false;
    }

    if (action == MotionEvent.ACTION_MOVE) {
      if (isScrollValueAchieved) {
        return true;
      }
      if (Math.abs(initialX - motion.getX()) > validScroll
          || Math.abs(initialY - motion.getY()) > validScroll) {
        isScrollValueAchieved = true;
      }
    }
    return isScrollValueAchieved;
  }

  @Override
  public boolean onTouchEvent(MotionEvent motion) {
    int n = 0;
    int n2 = 0;
    if (!getEnableScroll()) {
      if (getScrollY() == 0) {
        return false;
      } else {
        final View child = this.getChildAt(0);
        final int action = motion.getAction();
        final float x = motion.getX();
        final float y = motion.getY();
        if (action == MotionEvent.ACTION_DOWN) {
          initialXTE = x;
          initialYTE = y;
        }
        if (action == MotionEvent.ACTION_UP) {
          initialXTE = -1.0f;
          initialYTE = -1.0f;
        }
        if (action == MotionEvent.ACTION_MOVE) {
          if (initialXTE < 0.0f) {
            initialXTE = x;
          }
          if (initialYTE < 0.0f) {
            initialYTE = y;
          }
          final int b2 = (int) (initialYTE - y);
          initialXTE = x;
          initialYTE = y;

          if (b2 < 0) {
            if ((Math.abs(b2) <= getScrollY())) {
              scrollBy(0, b2);
            }
          }
        }
        return true;
      }
    }
    if (!useScroll) {
      return false;
    }
    final View child = this.getChildAt(0);
    final int action = motion.getAction();
    final float x = motion.getX();
    final float y = motion.getY();
    if (action == MotionEvent.ACTION_DOWN) {
      initialXTE = x;
      initialYTE = y;
    }
    if (action == MotionEvent.ACTION_UP) {
      initialXTE = -1.0f;
      initialYTE = -1.0f;
    }
    if (action == MotionEvent.ACTION_MOVE) {
      if (initialXTE < 0.0f) {
        initialXTE = x;
      }
      if (initialYTE < 0.0f) {
        initialYTE = y;
      }
      final int b = (int) (initialXTE - x);
      final int b2 = (int) (initialYTE - y);
      initialXTE = x;
      initialYTE = y;
      if (b < 0) {
        if ((Math.abs(b) <= getScrollX())) {
          scrollBy(b, 0);
        }
      } else {
        if (getScrollX() + ((View) getParent()).getWidth() < getWidth()) {
          scrollBy(b, 0);
        }
      }

      if (b2 < 0) {
        if ((Math.abs(b2) <= getScrollY())) {
          scrollBy(0, b2);
        }
      } else {
        if (getParent() != null) {
          if (getScrollY() + ((View) getParent()).getHeight() < getHeight()) {
            scrollBy(0, b2);
          }
        } else {
          if (getScrollY() + Utils.dpToPx(getContext(), 300f) < getHeight()) {
            scrollBy(0, b2);
          }
        }
      }
    }
    return true;
  }

  public boolean getUseScroll() {
    return this.useScroll;
  }

  public void setUseScroll(boolean useScroll) {
    this.useScroll = useScroll;
  }

  public boolean getEnableScroll() {
    boolean b = false;
    if (getParent() != null) {
      final int childCount = getChildCount();
      if (0 < childCount) {
        for (int i = 0; i < childCount; ++i) {
          final View child = getChildAt(i);
          final int width = child.getWidth();
          final int height = child.getHeight();
          if (((ViewGroup) getParent()).getWidth()
                  < getPaddingLeft()
                      + child.getX()
                      + child.getPaddingRight()
                      + width
                      + child.getPaddingLeft()
                      + getPaddingRight()
              || ((ViewGroup) getParent()).getHeight()
                  < getPaddingTop()
                      + child.getY()
                      + child.getPaddingTop()
                      + height
                      + child.getPaddingBottom()
                      + getPaddingBottom()) {
            b = true;
          }
        }
      }
    }
    return b;
  }
}
