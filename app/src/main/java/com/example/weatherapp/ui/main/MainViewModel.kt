package com.example.weatherapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repository = WeatherRepository()

    // state management
    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    fun fetchWeather(apiKey: String, location: String) {
        viewModelScope.launch {
            try {
                val response: WeatherResponse = repository.getWeather(apiKey, location)
                _weatherState.value = WeatherState.Success(response)
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(e.message.toString())
            }
        }
    }
}

// different state of weather data
sealed class WeatherState {
    object Loading: WeatherState()
    data class Success(val weather: WeatherResponse): WeatherState()
    data class Error(val message: String): WeatherState()

}