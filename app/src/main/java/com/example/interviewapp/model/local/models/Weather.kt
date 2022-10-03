package com.example.interviewapp.model.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "city_name")
    val cityName: String,
    @ColumnInfo(name = "state")
    val state: String,
    @ColumnInfo(name = "current_temp")
    val currentTemp: String,
    @ColumnInfo(name = "high_today")
    val highToday: String,
    @ColumnInfo(name = "low_today")
    val lowToday: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "last_updated")
    val lasUpdated: String,
    @ColumnInfo(name = "precipitation")
    val precipitation: String,
    @ColumnInfo(name = "date_time_epoch")
    val dateTimeEpoch: Int
)
