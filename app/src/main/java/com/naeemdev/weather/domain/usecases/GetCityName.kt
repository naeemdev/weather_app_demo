package com.naeemdev.weather.domain.usecases

import com.naeemdev.weather.domain.repositories.SavedCityRepository
import javax.inject.Inject

class GetCityName @Inject constructor(
    private val repository: SavedCityRepository
) {
    suspend operator fun invoke() = repository.getSavedCity()
}