package com.example.interviewapp.di

import com.example.interviewapp.model.remote.client.DefaultOkHttpClientManager
import com.example.interviewapp.model.remote.client.OkHttpClientManager
import com.example.interviewapp.model.remote.retrofit.RetrofitProvider
import com.example.interviewapp.model.remote.retrofit.DefaultRetrofitProvider
import com.example.interviewapp.model.remote.retrofit.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClientManager {
        return DefaultOkHttpClientManager()
    }

    @Singleton
    @Provides
    fun providesRetrofitInstance(okHttpClientManager: OkHttpClientManager): RetrofitProvider {
        return DefaultRetrofitProvider(okHttpClientManager.getOkHttpClient())
    }

    @Provides
    @Singleton
    fun providesWeatherService(retrofitProvider: RetrofitProvider): WeatherService {
        return retrofitProvider.retrofit.create(WeatherService::class.java)
    }
}