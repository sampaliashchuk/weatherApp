package com.example.interviewapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewapp.model.local.models.Weather
import com.example.interviewapp.model.repository.WeatherRepository
import com.example.interviewapp.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CapitalViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        getForecast()
    }

    private fun getForecast() {
        viewModelScope.launch {
            weatherRepository.fetchWeather().collect { result ->
                if (result is NetworkState.Success) {
                    _state.update { state ->
                        state.copy(isLoading = false, forecastToday = result.data)
                    }
                } else if (result is NetworkState.Failure) {
                    _state.update { state -> state.copy(isLoading = false) }
                    _error.emit(result.error)
                }
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = true,
        val forecastToday: List<Weather> = emptyList()
    )
}