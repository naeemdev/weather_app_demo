package com.naeemdev.weather.domain.usecases

import com.naeemdev.weather.domain.repositories.SavedCityRepository
import javax.inject.Inject

class SaveCityName @Inject constructor(
    private val repository: SavedCityRepository
) {
    suspend operator fun invoke(cityName: String) = repository.saveCity(cityName)
}