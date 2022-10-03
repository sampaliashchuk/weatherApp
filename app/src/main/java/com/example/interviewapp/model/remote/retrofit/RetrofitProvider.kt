package com.example.interviewapp.model.remote.retrofit

import retrofit2.Retrofit

interface RetrofitProvider {

    val baseUrl: String

    val retrofit: Retrofit
}