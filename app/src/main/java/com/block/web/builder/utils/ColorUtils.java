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

package com.block.web.builder.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.color.utilities.DynamicColor;

public class ColorUtils {

  public static int getColor(Context context, int res) {
    int color;

    if (DynamicColors.isDynamicColorAvailable()) {
      Resources.Theme theme = context.getTheme();
      TypedArray typedArray = theme.obtainStyledAttributes(new int[] {res});
      color = typedArray.getColor(0, 0);
      typedArray.recycle();
      if (color != 0) {
        return color;
      }
    }
    return MaterialColors.getColor(context, res, 0);
  }
}
