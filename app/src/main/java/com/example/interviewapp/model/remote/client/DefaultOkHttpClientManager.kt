package com.example.interviewapp.model.remote.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class DefaultOkHttpClientManager : OkHttpClientManager {

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    override fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}