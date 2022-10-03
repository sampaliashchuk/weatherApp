package com.example.interviewapp.model.repository

import android.content.Context
import android.net.Network
import com.example.interviewapp.R
import com.example.interviewapp.model.local.models.States
import com.example.interviewapp.model.local.models.Weather
import com.example.interviewapp.model.local.database.WeatherDao
import com.example.interviewapp.model.remote.models.WeatherResponse
import com.example.interviewapp.model.remote.retrofit.WeatherService
import com.example.interviewapp.utils.NetworkState
import com.example.interviewapp.utils.isNetworkAvailable
import com.example.interviewapp.utils.toDateTime
import com.example.interviewapp.utils.toSimpleDateTime
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.buffer
import okio.source
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    moshi: Moshi,
    private val context: Context,
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao,
) : WeatherRepository {

    private val inputStream = context.resources.openRawResource(R.raw.capitals)
    private val adapter = moshi.adapter(States::class.java)
    private val states = adapter.fromJson(inputStream.source().buffer())

    override suspend fun fetchWeather() = flow {
        if (context.isNetworkAvailable()) {
            weatherDao.deleteWeather()
            runCatching {
                states?.capitals?.map { capital ->
                    val currentTimeInMillis = System.currentTimeMillis()
                    val response = weatherService.getWeather(
                        location = capital,
                        date1 = currentTimeInMillis.toSimpleDateTime(),
                        date2 = currentTimeInMillis.toSimpleDateTime(4)
                    )
                    if (response.isSuccessful && response.body() != null) {
                        val weather = response.body()!!.mapToWeather()
                        weatherDao.insertWeather(weather)
                    }
                }
            }.onSuccess {
                emit(NetworkState.Success(weatherDao.getWeatherForToday()))
            }.onFailure { throwable ->
                emit(
                    NetworkState.Failure(
                        error = throwable.localizedMessage.orEmpty(),
                        data = weatherDao.getWeatherForToday()
                    )
                )
            }
        } else {
            emit(
                NetworkState.Failure(
                    error = context.getString(R.string.network_unavailable),
                    data = weatherDao.getWeatherForToday()
                )
            )
        }
    }

    override suspend fun getFiveDayForecast(city: String): Flow<List<Weather>> {
        return weatherDao.getFiveDayForecast(city)
    }

    private fun WeatherResponse.mapToWeather(): List<Weather> = with(this) {
        days.map { day ->
            val (city, state) = resolvedAddress.split(",")
            Weather(
                cityName = city,
                state = state,
                currentTemp = day.temp.toString(),
                highToday = day.tempmax.toString(),
                lowToday = day.tempmin.toString(),
                date = day.datetime,
                lasUpdated = System.currentTimeMillis().toDateTime(),
                precipitation = day.precip?.toString().orEmpty(),
                dateTimeEpoch = day.datetimeEpoch
            )
        }
    }
}