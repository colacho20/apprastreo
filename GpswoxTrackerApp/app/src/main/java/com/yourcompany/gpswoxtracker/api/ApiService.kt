package com.yourcompany.gpswoxtracker.api

import com.yourcompany.gpswoxtracker.models.ApiResponse
import com.yourcompany.gpswoxtracker.models.User
import com.yourcompany.gpswoxtracker.models.Vehicle
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("api/login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<ApiResponse<User>>

    @GET("api/user/vehicles")
    suspend fun getUserVehicles(
        @Header("Authorization") apiKey: String
    ): Response<ApiResponse<List<Vehicle>>>
}