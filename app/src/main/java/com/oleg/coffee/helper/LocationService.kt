package com.oleg.coffee.helper

import android.Manifest
import android.app.Notification
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.oleg.coffee.R
import com.oleg.coffee.helper.Constant.NotificationCode.CHANNEL_DEFAULT_IMPORTANCE
import com.oleg.coffee.helper.Constant.NotificationCode.ONGOING_NOTIFICATION_ID

/**
 * Crafted by Lukman on 28/04/2021.
 **/
class LocationService : Service() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    private fun start() {
//        val pendingIntent: PendingIntent =
//            Intent(this, ExampleActivity::class.java).let { notificationIntent ->
//                PendingIntent.getActivity(this, 0, notificationIntent, 0)
//            }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
            fusedLocationClient.lastLocation.addOnSuccessListener {
        }

        val notification: Notification = Notification.Builder(this, CHANNEL_DEFAULT_IMPORTANCE)
            .setContentTitle("Request Current Location")
            .setContentText("")
            .setSmallIcon(R.drawable.ic_coffee)
            .setContentIntent(null)
            .setTicker("ticker")
            .build()

        startForeground(ONGOING_NOTIFICATION_ID, notification)
    }
}