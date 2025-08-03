package com.naeemdev.weather.data.repositories



import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.naeemdev.weather.domain.repositories.SavedCityRepository
import com.naeemdev.weather.utils.Constants.SAVED_CITY_NAME_KEY
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SavedCityRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SavedCityRepository {

    companion object {
        private val CITY_KEY = stringPreferencesKey(SAVED_CITY_NAME_KEY)
    }


    override suspend fun getSavedCity(): String {

        return dataStore.data
            .map { preferences ->
                preferences[CITY_KEY] ?: ""
            }
            .first()
    }

    override suspend fun saveCity(cityName: String) {

        dataStore.edit { preferences ->
            preferences[CITY_KEY] = cityName
        }
    }
}
