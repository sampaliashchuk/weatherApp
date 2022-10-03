package com.example.interviewapp.di

import android.content.Context
import androidx.room.Room
import com.example.interviewapp.model.local.database.DB_NAME
import com.example.interviewapp.model.local.database.WeatherDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesWeatherDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        WeatherDB::class.java,
        DB_NAME
    ).build()

    @Singleton
    @Provides
    fun providesWeatherDao(database: WeatherDB) = database.weatherDao()
}