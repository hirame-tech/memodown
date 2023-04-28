package com.example.memodown

import android.content.res.Resources
import android.util.DisplayMetrics
import kotlin.math.roundToInt

public class AndroidUtil{
    fun pixels2Dp(px: Float): Float {
        val metrics = Resources.getSystem().displayMetrics
        val dp: Float = px / (metrics.densityDpi/160f)
        return dp.roundToInt().toFloat()
    }

    fun dp2Pixels(dp: Float): Float{
        val metrics = Resources.getSystem().displayMetrics
        val px: Float = dp * (metrics.densityDpi/160f)
        return px.roundToInt().toFloat()
    }

    private fun convertDpTopx(dp: Int): Int{
        return (dp*(Resources.getSystem().displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }

    private fun convertPxToDp(px: Int): Int{
        return (px/(Resources.getSystem().displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }
}