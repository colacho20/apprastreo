// DevicesResponse.kt
package com.yourcompany.gpswoxtracker.data.remote

data class DevicesResponse(
    val status: String,
    val devices: List<Device>
)

data class Device(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double
)
