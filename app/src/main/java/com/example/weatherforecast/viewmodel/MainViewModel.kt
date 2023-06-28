package com.example.weatherforecast.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.data.dto.ApiResponse
import com.example.weatherforecast.data.remote.NetworkResult
import com.example.weatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    var apiResult: ApiResponse? = null

    fun getWeatherForecast(
        accessToken: String,
        query: String
    ): LiveData<NetworkResult<ApiResponse>> {
        return MutableLiveData<NetworkResult<ApiResponse>>().apply {
            postValue(NetworkResult.Loading())
            viewModelScope.launch {
                val response = weatherRepository.getCurrentLocationWeather(accessToken, query)
                if (response.isSuccessful) {
                    Log.d("TAG", "Success Response :: ${response.body()}")
                    postValue(NetworkResult.Success(response.body()))
                    apiResult = response.body()
                } else if (response.errorBody() != null) {
                    Log.d("TAG", "Error: ${response.errorBody()}")
                    postValue(NetworkResult.Error(response.message()))
                }
            }
        }
    }
}
