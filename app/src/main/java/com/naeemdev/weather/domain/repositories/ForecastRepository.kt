package com.naeemdev.weather.domain.repositories

import com.naeemdev.weather.domain.model.ForecastModelD
import com.naeemdev.weather.domain.Resource
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    suspend fun getForecastDataWithCityName(cityName: String): Resource<ForecastModelD>
}