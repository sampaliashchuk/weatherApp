package com.example.interviewapp.model.remote.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class DefaultRetrofitProvider @Inject constructor(okHttpClient: OkHttpClient) : RetrofitProvider {

    override val baseUrl: String = "https://weather.visualcrossing.com"

    override val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}