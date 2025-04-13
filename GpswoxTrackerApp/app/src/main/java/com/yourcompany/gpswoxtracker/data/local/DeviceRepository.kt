package com.yourcompany.gpswoxtracker.data.local

import android.content.Context
import android.content.SharedPreferences
import com.yourcompany.gpswoxtracker.data.remote.ApiService
import com.yourcompany.gpswoxtracker.data.model.DeviceResponse

class DeviceRepository(
    context: Context,
    private val apiService: ApiService
) {

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences("gpswox_prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPrefs.edit().putString("api_token", token).apply()
    }

    fun getToken(): String {
        return sharedPrefs.getString("api_token", "") ?: ""
    }

    fun clearToken() {
        sharedPrefs.edit().remove("api_token").apply()
    }

    // ✅ Nuevo método para obtener los vehículos
    suspend fun getVehicles(): List<DeviceResponse.Device> {
        val token = getToken()
        if (token.isBlank()) throw Exception("Token no disponible")
        val response = apiService.getDevices(token)
        if (response.isSuccessful) {
            return response.body()?.devices ?: emptyList()
        } else {
            throw Exception("Error al obtener dispositivos: ${response.code()}")
        }
    }
}
