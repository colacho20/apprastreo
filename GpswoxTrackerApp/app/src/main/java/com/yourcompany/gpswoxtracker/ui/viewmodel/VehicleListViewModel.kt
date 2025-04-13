package com.yourcompany.gpswoxtracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourcompany.gpswoxtracker.data.remote.ApiService
import com.yourcompany.gpswoxtracker.data.local.DeviceRepository
import kotlinx.coroutines.launch // ✅ IMPORT NECESARIO

class VehicleListViewModel(
    private val apiService: ApiService,
    private val deviceRepository: DeviceRepository // ✅ Este es el nombre correcto
) : ViewModel() {

    fun fetchVehicles() {
        viewModelScope.launch { // ✅ Esto ahora está disponible gracias al import
            try {
                val vehicles = deviceRepository.getVehicles() // ✅ Usamos el nombre correcto
                // TODO: Actualizar UI con los vehículos obtenidos
            } catch (e: Exception) {
                // TODO: Manejar error
            }
        }
    }
}
