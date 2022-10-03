package com.example.interviewapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewapp.model.local.models.Weather
import com.example.interviewapp.model.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForeCastViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weather = MutableStateFlow(emptyList<Weather>())
    val weather = _weather.asStateFlow()

    fun getFiveDayForecast(city: String) {
        viewModelScope.launch {
            weatherRepository.getFiveDayForecast(city).collect { weather ->
                _weather.value = weather
            }
        }
    }
}