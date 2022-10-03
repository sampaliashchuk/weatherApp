package com.example.interviewapp.model.repository

import com.example.interviewapp.model.local.models.Weather
import com.example.interviewapp.utils.NetworkState
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getFiveDayForecast(city: String): Flow<List<Weather>>

    suspend fun fetchWeather(): Flow<NetworkState<List<Weather>>>
}