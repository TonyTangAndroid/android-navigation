package com.example.android.codelabs.navigation;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.navigation.NavDeepLinkBuilder;

public class DeepLinkAppWidgetProvider extends AppWidgetProvider {

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    RemoteViews remoteViews =
        new RemoteViews(context.getPackageName(), R.layout.deep_link_appwidget);
    Bundle args = new Bundle();
    args.putString("myarg", "From Widget");
    PendingIntent pendingIntent =
        new NavDeepLinkBuilder(context)
            .setGraph(R.navigation.mobile_navigation)
            .setDestination(R.id.deeplink_dest)
            .setArguments(args)
            .createPendingIntent();
    remoteViews.setOnClickPendingIntent(R.id.deep_link_button, pendingIntent);
    appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
  }
}
