package com.example.weatherapp.data.network

import com.example.weatherapp.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* Singleton object to provide the Retrofit instance,
   which uses this WeatherApiService interface.*/
object RetrofitInstance {
    // Lazy initialization of the Retrofit instance
    val api: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // JSON to Kotlin object
            .build()
            .create(WeatherApiService::class.java)
    }
}
