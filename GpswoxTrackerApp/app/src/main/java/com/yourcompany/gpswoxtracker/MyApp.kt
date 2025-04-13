package com.yourcompany.gpswoxtracker

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.yourcompany.gpswoxtracker.di.networkModule
import com.yourcompany.gpswoxtracker.di.repositoryModule

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(networkModule, repositoryModule)  // Carga los módulos de Koin
        }
    }
}
