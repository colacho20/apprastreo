package com.yourcompany.gpswoxtracker.models

data class ApiResponse<T>(
    val status: Boolean,
    val message: String,
    val data: T
)