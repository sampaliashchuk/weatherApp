package com.example.interviewapp.model.remote.retrofit

import com.example.interviewapp.model.remote.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val API_KEY = "3YRABEHZTQEBZ3S72A67Q778B"

interface WeatherService {
    @GET("VisualCrossingWebServices/rest/services/timeline/{location}/{date1}/{date2}")
    suspend fun getWeather(
        @Path("location") location: String,
        @Path("date1") date1: String,
        @Path("date2") date2: String,
        @Query("unitGroup") unitGroup: String = "us",
        @Query("key") key: String = API_KEY,
        @Query("current") current: String = "current"
    ): Response<WeatherResponse>
}