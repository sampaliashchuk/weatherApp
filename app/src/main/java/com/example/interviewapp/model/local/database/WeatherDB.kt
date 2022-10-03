package com.example.interviewapp.model.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.interviewapp.model.local.models.Weather

const val DB_NAME = "weather_database"
@Database(entities = [Weather::class], version = 1)
abstract class WeatherDB : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}