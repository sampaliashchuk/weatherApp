package com.example.interviewapp.model.remote.client

import okhttp3.OkHttpClient

fun interface OkHttpClientManager {
    fun getOkHttpClient(): OkHttpClient
}