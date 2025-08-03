package com.naeemdev.weather.domain.repositories

interface SavedCityRepository {
    suspend fun getSavedCity(): String
    suspend fun saveCity(cityName: String)
}