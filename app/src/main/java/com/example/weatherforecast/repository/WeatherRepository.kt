package com.example.weatherforecast.repository

import com.example.weatherforecast.data.dto.ApiResponse
import com.example.weatherforecast.data.remote.ApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCurrentLocationWeather(accessKey: String, query: String): Response<ApiResponse> =
        apiService.getCurrentLocationWeather(accessKey, query)
}