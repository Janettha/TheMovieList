package com.janettha.jetpackcompose.themoviedb.ui.location

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.janettha.jetpackcompose.themoviedb.core.di.modules.MainDispatcher
import com.janettha.jetpackcompose.themoviedb.core.util.extensions.hasLocationPermissions
import com.janettha.jetpackcompose.themoviedb.domain.LocationUseCases
import com.janettha.jetpackcompose.themoviedb.sys.config.Constants.Notification.Constants.NOTIFICATION_CHANNEL_ID
import com.janettha.jetpackcompose.themoviedb.sys.config.Constants.Notification.Constants.NOTIFICATION_CHANNEL_NAME
import com.janettha.jetpackcompose.themoviedb.sys.config.Constants.Notification.Constants.NOTIFICATION_ID
import com.janettha.jetpackcompose.themoviedb.sys.config.Constants.TrackLocationService.Constants.ACTION_PAUSE_SERVICE
import com.janettha.jetpackcompose.themoviedb.sys.config.Constants.TrackLocationService.Constants.ACTION_START_OR_RESUME_SERVICE
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class TrackingLocationService() : LifecycleService() {
    private val mTag = "TrackingLocationService"

    // region COMPONENTS
    @Inject
    lateinit var localCases: LocationUseCases

    @Inject
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    @Inject
    lateinit var notificationManager: NotificationManager

    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder

    @Inject
    lateinit var notificationObserver: Observer<Long>
    // endregion

    lateinit var locationCallback: LocationCallback

    private var timeInSeconds = MutableLiveData<Long>()
    private var timeInMillis: Long = 0L
    private var isTracking: Boolean = false
    private var lastSecondTimeStamp = 0L

    companion object {
        var isServiceRunning = false
    }

    private fun postInitialValues() {
        Log.d(mTag, "postInitialValues: ")
        timeInSeconds.postValue(0L)
        timeInMillis = 0L
        isTracking = false
        lastSecondTimeStamp = 0L
    }

    override fun onCreate() {
        super.onCreate()

        postInitialValues()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                val sdf = SimpleDateFormat(
                    "dd/MM/yyyy HH:mm:ss",
                    Locale("es", "ES")
                )
                sdf.timeZone = TimeZone.getTimeZone("America/Mexico_City")

                val currentDate = sdf.format(Date())
                val lastLocation = locationResult.lastLocation
                val latitude = lastLocation.latitude.toBigDecimal().toPlainString()
                val longitude = lastLocation.longitude.toBigDecimal().toPlainString()

                startForegroundService()

                CoroutineScope(Dispatchers.Main).launch {
                    localCases.locationUseCase(latitude, longitude, currentDate)
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    subscribeToLocationUpdates()
                }
                ACTION_PAUSE_SERVICE -> Log.d(mTag, "onStartCommand: ACTION_PAUSE_SERVICE")
                else -> { //ACTION_STOP_SERVICE ->
                    stopService()
                }
            }
        }
        return super.onStartCommand(intent, flags, START_NOT_STICKY)
    }

    override fun onDestroy() {
        super.onDestroy()
        isServiceRunning = false
    }

    private fun stopService() {
        isServiceRunning = false
        postInitialValues()
        timeInSeconds.removeObserver(notificationObserver)
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        stopForeground(true)
        stopSelf()
    }

    private fun startForegroundService() {
        isTracking = true
        isServiceRunning = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotification(notificationManager)
        }
        startForeground(NOTIFICATION_ID, notificationBuilder.build())
        timeInSeconds.observe(this, notificationObserver)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }

    @SuppressLint("MissingPermission")
    private fun subscribeToLocationUpdates() {
        if (hasLocationPermissions(this)) {
            val locationRequest = LocationRequest.create().apply {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                interval = TimeUnit.MINUTES.toMillis(5)
            }
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest, locationCallback,
                Looper.getMainLooper()
            )
        }
    }

}