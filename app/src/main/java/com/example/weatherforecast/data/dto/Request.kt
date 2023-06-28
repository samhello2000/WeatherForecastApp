package com.example.weatherforecast.data.dto

data class Request(
    val type: String,
    val query: String,
    val language: String,
    val unit: String
)
