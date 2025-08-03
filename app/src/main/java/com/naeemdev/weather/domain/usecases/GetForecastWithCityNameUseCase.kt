package com.naeemdev.weather.domain.usecases

import com.naeemdev.weather.data.repositories.ForecastRepositoryImpl
import javax.inject.Inject

class GetForecastWithCityNameUseCase @Inject constructor(
    private val forecastRepositoryImpl: ForecastRepositoryImpl
) {
    suspend operator fun invoke(cityName: String) = forecastRepositoryImpl.getForecastDataWithCityName(cityName)
}