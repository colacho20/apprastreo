package com.yourcompany.gpswoxtracker.models

data class Vehicle(
    val vehicle_id: String,
    val name: String,
    val plate_number: String,
    val last_location: LocationData?,
    val status: String,
    val image_url: String?
)

data class LocationData(
    val lat: Double,
    val lng: Double,
    val timestamp: String,
    val address: String?
)