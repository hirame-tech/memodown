package com.example.memodown;
import android.appwidget.AppWidgetManager;
import android.os.Bundle;

import static com.example.memodown.AndroidUtil.dp2Pixels;

public class Sizes
{
    int minWidth;
    int maxWidth;
    int minHeight;
    int maxHeight;

    Sizes(Bundle options)
    {
        minWidth=(int)dp2Pixels(options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH));
        maxWidth=(int)dp2Pixels(options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH));
        minHeight=(int)dp2Pixels(options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT));
        maxHeight=(int)dp2Pixels(options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT));
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof Sizes)
        {
            Sizes co=(Sizes)obj;
            return (minWidth==co.minWidth && maxWidth==co.maxWidth && minHeight==co.minHeight && maxHeight==co.maxHeight);
        }

        return false;
    }


}