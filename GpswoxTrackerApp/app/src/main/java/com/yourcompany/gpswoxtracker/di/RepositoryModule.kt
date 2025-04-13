// RepositoryModule.kt
package com.yourcompany.gpswoxtracker.di

import com.yourcompany.gpswoxtracker.data.local.DeviceRepository
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

val repositoryModule = module {
    single { DeviceRepository(androidContext(), get()) }
}
