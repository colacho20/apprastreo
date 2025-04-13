// DeviceResponse.kt
package com.yourcompany.gpswoxtracker.data.model

data class DeviceResponse(
    val success: Boolean,
    val message: String,
    val devices: List<Device> // Aquí debes definir cómo se estructura tu lista de dispositivos
) {

    data class Device(
        val id: String,
        val name: String,
        val status: String,
        val location: String // Asegúrate de que estos campos coincidan con la respuesta de la API
    )
}
