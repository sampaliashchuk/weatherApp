package com.example.interviewapp.model.remote.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val address: String,
    val currentConditions: CurrentConditions?,
    val days: List<Day>,
    val latitude: Double,
    val longitude: Double,
    val queryCost: Int,
    val resolvedAddress: String,
    val timezone: String,
    val tzoffset: Double
) {
    @JsonClass(generateAdapter = true)
    data class Day(
        val cloudcover: Double,
        val conditions: String,
        val datetime: String,
        val datetimeEpoch: Int,
        val description: String,
        val dew: Double,
        val feelslike: Double,
        val feelslikemax: Double,
        val feelslikemin: Double,
        val humidity: Double,
        val icon: String,
        val moonphase: Double,
        val precip: Double?,
        val precipcover: Double?,
        val precipprob: Double?,
        val preciptype: List<String>? = emptyList(),
        val pressure: Double,
        val severerisk: Double,
        val snow: Double,
        val snowdepth: Double?,
        val solarenergy: Double?,
        val solarradiation: Double,
        val source: String,
        val stations: List<String>? = emptyList(),
        val sunrise: String,
        val sunriseEpoch: Int,
        val sunset: String,
        val sunsetEpoch: Int,
        val temp: Double,
        val tempmax: Double,
        val tempmin: Double,
        val uvindex: Double,
        val visibility: Double,
        val winddir: Double,
        val windgust: Double?,
        val windspeed: Double
    )

    @JsonClass(generateAdapter = true)
    data class CurrentConditions(
        val cloudcover: Double,
        val conditions: String,
        val datetime: String,
        val datetimeEpoch: Int,
        val dew: Double,
        val feelslike: Double,
        val humidity: Double,
        val icon: String,
        val moonphase: Double,
        val precip: Double,
        val precipprob: Any?,
        val preciptype: Any?,
        val pressure: Double,
        val snow: Double,
        val snowdepth: Double,
        val solarenergy: Any?,
        val solarradiation: Double,
        val sunrise: String,
        val sunriseEpoch: Int,
        val sunset: String,
        val sunsetEpoch: Int,
        val temp: Double,
        val uvindex: Double,
        val visibility: Double,
        val winddir: Double,
        val windgust: Double?,
        val windspeed: Double
    )
}





