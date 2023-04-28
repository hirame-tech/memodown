package com.example.memodown

import android.appwidget.AppWidgetManager
import android.os.Bundle
import com.example.memodown.AndroidUtil.dp2Pixels
import java.util.*


public class Sizes {
    var minWidth: Int = 0
    var maxWidth: Int = 0
    var minHeight: Int = 0
    var maxHeight: Int = 0

    fun Sizes(options: Bundle){
        minWidth = dp2Pixels(options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH))
        maxWidth = dp2Pixels(options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH))
        minHeight = dp2Pixels(options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT))
        maxHeight = dp2Pixels(options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT)
    }

    public fun equals(obj: Objects): Boolean{
        if(obj is Sizes) {
            var co: Sizes = obj
            return ((minWidth == co.minWidth) && (maxWidth == co.maxWidth) && (minHeight == co.minHeight) && (maxHeight == co.maxHeight))
        }
        return false
    }

}