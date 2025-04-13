// GpswoxApplication.kt
package com.yourcompany.gpswoxtracker

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.yourcompany.gpswoxtracker.di.networkModule
import com.yourcompany.gpswoxtracker.di.repositoryModule
import com.yourcompany.gpswoxtracker.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GpswoxApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Inicialización de Koin con todos los módulos
        startKoin {
            androidContext(this@GpswoxApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }

        // Crear canal de notificaciones si es Android 8.0 o superior
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "default_channel_id"
            val channelName = "Notificaciones"
            val channelDescription = "Canal para notificaciones generales"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
