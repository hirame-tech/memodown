package com.example.memodown;

import android.content.res.Resources;
import android.util.DisplayMetrics;


public class AndroidUtil
{

    public static float pixels2Dp(float px)
    {
        DisplayMetrics metrics=Resources.getSystem().getDisplayMetrics();
        float dp=px/(metrics.densityDpi/160f);
        return Math.round(dp);
    }

    public static float dp2Pixels(float dp)
    {
        DisplayMetrics metrics=Resources.getSystem().getDisplayMetrics();
        float px=dp*(metrics.densityDpi/160f);
        return Math.round(px);
    }


    private int convertDpToPx(int dp)
    {
        return Math.round(dp*(Resources.getSystem().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));

    }

    private int convertPxToDp(int px)
    {
        return Math.round(px/(Resources.getSystem().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));
    }

}