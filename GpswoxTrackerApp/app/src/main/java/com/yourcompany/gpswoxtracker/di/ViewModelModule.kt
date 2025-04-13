// ViewModelModule.kt
package com.yourcompany.gpswoxtracker.di

import com.yourcompany.gpswoxtracker.ui.viewmodels.MainViewModel
import com.yourcompany.gpswoxtracker.ui.viewmodels.LoginViewModel
import com.yourcompany.gpswoxtracker.ui.viewmodels.VehicleListViewModel
import com.yourcompany.gpswoxtracker.ui.viewmodels.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Define el módulo de ViewModel para Koin
val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { VehicleListViewModel(get(), get()) }
    viewModel { MapViewModel(get(), get()) }
}
