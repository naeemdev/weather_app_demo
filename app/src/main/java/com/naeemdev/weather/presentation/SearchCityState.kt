package com.naeemdev.weather.presentation

import com.naeemdev.weather.domain.model.ForecastModelD

sealed interface SearchCityState {
    data class Success(val forecast: ForecastModelD?): SearchCityState
    data class Error(val errorMessage: Int?): SearchCityState
    object Loading: SearchCityState
    object Idle: SearchCityState
}