package com.example.memodown

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.RemoteViews

import com.example.memodown.databinding.NewAppWidgetBinding


/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {
    private lateinit var binding: NewAppWidgetBinding
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int){
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)

    //var canvasBitmap: Bitmap = 0
    // var canvas: Canvas = null

    var options: Bundle = appWidgetManager.getAppWidgetOptions(appWidgetId)
    val newSizes = Sizes.Size(options)

    var canvasBitmap = Bitmap.createBitmap(newSizes.minWidth, newSizes.maxHeight, Bitmap.Config.ARGB_8888)
    var canvas =Canvas(canvasBitmap)
    canvas.drawColor(0, PorterDuff.Mode.CLEAR)

    views.setImageViewBitmap(R.id.draw,canvasBitmap)


    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}