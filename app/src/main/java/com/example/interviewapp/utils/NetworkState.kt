package com.example.interviewapp.utils

sealed interface NetworkState<out T> {
    data class Success<T>(val data: T) : NetworkState<T>
    data class Failure<T>(val error: String, val data: T? = null) : NetworkState<T>
}