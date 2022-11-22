package com.janettha.jetpackcompose.themoviedb.core.di.modules

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.janettha.jetpackcompose.themoviedb.MainActivity
import com.janettha.jetpackcompose.themoviedb.R
import com.janettha.jetpackcompose.themoviedb.sys.config.Constants.Notification.Constants.NOTIFICATION_CHANNEL_ID
import com.janettha.jetpackcompose.themoviedb.sys.config.Constants.Notification.Constants.NOTIFICATION_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
class TrakingServiceModule {

    @ServiceScoped
    @Provides
    fun provideFusedLocationProviderClient(
        @ApplicationContext app: Context
    ) = FusedLocationProviderClient(app)

    @ServiceScoped
    @Provides
    fun provideMainActivityPendingIntent(
        @ApplicationContext app: Context
    ): PendingIntent = PendingIntent.getActivity(
        app, 0, Intent(app, MainActivity::class.java),
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S)
            PendingIntent.FLAG_MUTABLE
        else PendingIntent.FLAG_ONE_SHOT
    )

    @ServiceScoped
    @Provides
    fun provideNotificationManager(
        @ApplicationContext app: Context
    ) = app.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @ServiceScoped
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext app: Context,
        pendingIntent: PendingIntent
    ) = NotificationCompat.Builder(app, NOTIFICATION_CHANNEL_ID)
        .setAutoCancel(true)
        .setOngoing(false)
        .setSmallIcon(R.drawable.ic_my_location)
        .setContentTitle(app.getString(R.string.real_time_location))
        .setContentText("Close")
        .setContentIntent(pendingIntent)

    @ServiceScoped
    @Provides
    fun provideNotificationObserver(
        notificationManager: NotificationManager,
        notificationBuilder: NotificationCompat.Builder
    ) = Observer<Long> {
        val notification =
            notificationBuilder.setContentText("Ir a la sección de mapas para ver más detalles.")
        notificationManager.notify(NOTIFICATION_ID, notification.build())
    }
}