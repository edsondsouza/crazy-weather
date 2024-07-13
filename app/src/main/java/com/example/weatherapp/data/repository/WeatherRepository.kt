package com.example.weatherapp.data.repository

import com.example.weatherapp.data.network.RetrofitInstance

class WeatherRepository {
    suspend fun getWeather(apiKey: String, location: String) = RetrofitInstance.api.getWeather(apiKey, location)
}