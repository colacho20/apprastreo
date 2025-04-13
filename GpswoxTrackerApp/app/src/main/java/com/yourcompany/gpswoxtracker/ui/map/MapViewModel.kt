// MapViewModel.kt
package com.yourcompany.gpswoxtracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.yourcompany.gpswoxtracker.data.remote.ApiService
import com.yourcompany.gpswoxtracker.data.local.DeviceRepository
import kotlinx.coroutines.Dispatchers

class MapViewModel(
    private val apiService: ApiService,
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    // Ejemplo: Obtener lista de dispositivos y mostrarlos en el mapa
    val devices = liveData(Dispatchers.IO) {
        try {
            val response = apiService.getDevices(deviceRepository.getToken())
            if (response.isSuccessful && response.body() != null) {
                emit(response.body()?.devices)
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}
