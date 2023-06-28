package com.example.weatherforecast.data.remote

import com.example.weatherforecast.data.dto.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/current")
    suspend fun getCurrentLocationWeather(
        @Query("access_key") accessKey: String,
        @Query("query") query: String
    ): Response<ApiResponse>

}