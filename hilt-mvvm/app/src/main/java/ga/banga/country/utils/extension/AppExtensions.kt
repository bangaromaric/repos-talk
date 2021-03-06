package ga.banga.country.utils.extension

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.use

/**
 * Created by Romaric BANGA on 8/22/21 at 10:53 PM
 */

/**
 * Retrieve a color from the current [android.content.res.Resources.Theme].
 */
@ColorInt
@SuppressLint("Recycle")
fun Context.themeColor(
  @AttrRes themeAttrId: Int
): Int {
  return obtainStyledAttributes(
    intArrayOf(themeAttrId)
  ).use {
    it.getColor(0, Color.MAGENTA)
  }
}