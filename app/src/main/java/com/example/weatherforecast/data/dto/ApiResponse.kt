package com.example.weatherforecast.data.dto

data class ApiResponse(
    val request: Request,
    val location: Location,
    val current: Current
)
