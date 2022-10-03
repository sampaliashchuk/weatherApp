package com.example.interviewapp.di

import android.content.Context
import com.example.interviewapp.model.local.database.WeatherDao
import com.example.interviewapp.model.remote.retrofit.WeatherService
import com.example.interviewapp.model.repository.WeatherRepository
import com.example.interviewapp.model.repository.WeatherRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun providesWeatherRepository(
        @ApplicationContext context: Context,
        moshi: Moshi,
        weatherService: WeatherService,
        weatherDao: WeatherDao
    ): WeatherRepository {
        return WeatherRepositoryImpl(moshi, context, weatherService, weatherDao)
    }
}