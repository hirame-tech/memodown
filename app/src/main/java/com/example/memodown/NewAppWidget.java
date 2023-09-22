package com.example.memodown;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.SparseArray;
import android.webkit.WebView;
import android.widget.RemoteViews;

import org.markdownj.MarkdownProcessor;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    private static SparseArray<WebView> cachedWebViews=null;

    private static WebView prepareWebView(Context context, AppWidgetManager appWidgetManager, int appWidgetId)
    {
        if (cachedWebViews==null) { cachedWebViews=new SparseArray<>(); }
        WebView webView=cachedWebViews.get(appWidgetId);

        if (webView==null)
        {
            webView=new WebView(context);
            cachedWebViews.put(appWidgetId, webView);

            webView.setBackgroundColor(Color.TRANSPARENT);

            MarkdownProcessor processor = new MarkdownProcessor();
            String unencodedHtml= processor.markdown("- Heeeeeeee");
            String encodedHtml=Base64.encodeToString(unencodedHtml.getBytes(), Base64.DEFAULT);
            webView.loadData(encodedHtml, "text/html", "base64");

            webView.setDrawingCacheEnabled(true);
            webView.buildDrawingCache();
            webView.getSettings().setJavaScriptEnabled(true);
        }

        return webView;
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views=new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        Bitmap canvasBitmap=null;
        Canvas canvas=null;
        Paint paint=null;

        Bundle options=appWidgetManager.getAppWidgetOptions(appWidgetId);
        Sizes newSizes=new Sizes(options);

        canvasBitmap=Bitmap.createBitmap(newSizes.minWidth, newSizes.maxHeight, Bitmap.Config.ARGB_8888);
        canvas=new Canvas(canvasBitmap);

        paint=new Paint();
        paint.setAntiAlias(true);

        canvas.drawColor(0, PorterDuff.Mode.CLEAR);

        WebView webView=prepareWebView(context, appWidgetManager, appWidgetId);
        webView.layout(0, 0, newSizes.minWidth, newSizes.maxHeight);

        int contentHeight=webView.getContentHeight();

        if (contentHeight==0)
        {
            // we need to wait!

            Intent intentUpdate=new Intent(context, NewAppWidget.class);

            // The intent action must be an app widget update.
            intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

            // Include the widget ID to be updated as an intent extra.
            int[] idArray=new int[]{appWidgetId};
            intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

            final PendingIntent pendingUpdate=PendingIntent.getBroadcast(context, appWidgetId, intentUpdate, PendingIntent.FLAG_IMMUTABLE);
            //Error

            // We could use an Alarm but Handlers are way more precise for sub-second schedulings
            Handler handler=new Handler();
            handler.postDelayed(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        pendingUpdate.send();
                    }
                    catch (PendingIntent.CanceledException e)
                    {
                        e.printStackTrace();
                    }
                }
            }, 300);

            // return instead of drawing
            return;
        }

        webView.draw(canvas);

        views.setImageViewBitmap(R.id.draw, canvasBitmap);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}