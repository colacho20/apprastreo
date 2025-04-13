// MainViewModel.kt
package com.yourcompany.gpswoxtracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.yourcompany.gpswoxtracker.data.remote.ApiService
import com.yourcompany.gpswoxtracker.data.local.DeviceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel(
    private val apiService: ApiService,
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    // Métodos y lógica de tu ViewModel
    suspend fun fetchDevices(apiToken: String) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDevices(apiToken)
                if (response.isSuccessful) {
                    val devices = response.body()?.devices
                    // Haz algo con los dispositivos aquí, por ejemplo, guardarlos en un repositorio
                } else {
                    // Maneja el error de la respuesta
                }
            } catch (e: Exception) {
                // Maneja el error de la solicitud
            }
        }
    }
}
