package com.example.weatherapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.ui.main.WeatherState
import com.example.weatherapp.utils.Constants.API_KEY

@Composable
fun WeatherScreen(
    weatherState: WeatherState, fetchWeather: (String, String) -> Unit) {
    var location by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Enter city/country") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { fetchWeather(API_KEY, location) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Weather")
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (weatherState) {
            is WeatherState.Loading -> CircularProgressIndicator()
            is WeatherState.Success -> WeatherInfo(weatherState.weather)
            is WeatherState.Error -> Text("Error: ${weatherState.message}")
        }
    }

}

@Composable
fun WeatherInfo(weather: WeatherResponse) {
    Column {
        Text("Name: ${weather.location.name}")
        Text("Region: ${weather.location.region}")
        Text("Country: ${weather.location.country}")
        Text("Temperature: ${weather.current.temp_c}°C")
        Text("Time: ${weather.current.last_updated}")
        Text("Humidity: ${weather.current.humidity}%")
        Text("Wind: ${weather.current.wind_kph} kph")
    }
}
