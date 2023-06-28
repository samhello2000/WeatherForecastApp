package com.example.weatherforecast.data.remote

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CustomInterceptor @Inject constructor(
    @ApplicationContext val context: Context,
) : Interceptor {

    private lateinit var apiResponse: Response

    companion object {
        private const val AUTHORIZATION = "Authorization"
        const val UNKNOWN_ERROR_CODE = 1
        const val NETWORK_ERROR_CODE = 2
        private const val ERROR_BODY = "{\n" +
                "  \"errorMessage\": \"Something went wrong. Please try again.\"\n" +
                "}"
        private const val JSON = "application/json"

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().build()

        apiResponse = chain.proceed(request)
        return apiResponse
    }
}