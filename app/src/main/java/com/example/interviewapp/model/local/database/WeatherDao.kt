package com.example.interviewapp.model.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.interviewapp.model.local.models.Weather
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertWeather(weatherList: List<Weather>)

    @Query("SELECT * FROM weather ORDER BY date_time_epoch DESC LIMIT 50")
    abstract suspend fun getWeatherForToday(): List<Weather>

    @Query("SELECT * FROM weather WHERE city_name = :city")
    abstract fun getFiveDayForecast(city: String): Flow<List<Weather>>

    @Query("DELETE FROM weather")
    abstract suspend fun deleteWeather()
}