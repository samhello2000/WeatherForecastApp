package com.example.weatherforecast

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherforecast.data.remote.NetworkResult
import com.example.weatherforecast.databinding.ActivityMainBinding
import com.example.weatherforecast.utils.Constants
import com.example.weatherforecast.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
        mainViewModel.getWeatherForecast(Constants.ACCESS_TOKEN, Constants.LOCATION)
            .observe(this@MainActivity) {
                when (it) {
                    is NetworkResult.Success -> {
                        mBinding.weatherTextView.text = getString(
                            R.string.temperature,
                            mainViewModel.apiResult?.current?.temperature.toString()
                        )
                        mBinding.locationTextView.text =
                            mainViewModel.apiResult?.location?.name.toString()

                    }

                    is NetworkResult.Loading -> {
                        Toast.makeText(this, getString(R.string.loading), Toast.LENGTH_SHORT).show()
                    }

                    is NetworkResult.Error -> {
                        Toast.makeText(
                            this,
                            getString(R.string.something_went_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {

                    }
                }
            }
    }
}