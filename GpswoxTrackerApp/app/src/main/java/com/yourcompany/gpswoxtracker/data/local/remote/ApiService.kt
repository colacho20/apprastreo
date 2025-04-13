// ApiService.kt
package com.yourcompany.gpswoxtracker.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.yourcompany.gpswoxtracker.data.model.LoginResponse
import com.yourcompany.gpswoxtracker.data.model.DeviceResponse

interface ApiService {

    // Endpoint para obtener dispositivos
    @GET("api/v1/getDevices")
    suspend fun getDevices(
        @Query("api_token") apiToken: String
    ): Response<DeviceResponse>

    // Endpoint para realizar el login
    @GET("api/v1/login")
    suspend fun login(
        @Query("api_token") apiToken: String
    ): Response<LoginResponse>
}
