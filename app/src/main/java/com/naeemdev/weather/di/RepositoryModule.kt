package com.naeemdev.weather.di


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.naeemdev.weather.data.remote.WeatherApi
import com.naeemdev.weather.data.repositories.ForecastRepositoryImpl
import com.naeemdev.weather.data.repositories.SavedCityRepositoryImpl
import com.naeemdev.weather.domain.repositories.ForecastRepository
import com.naeemdev.weather.domain.repositories.SavedCityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideForecastRepository(weatherApi: WeatherApi): ForecastRepository {
        return ForecastRepositoryImpl(weatherApi)
    }

    @Provides
    @Singleton
    fun provideSavedCityRepository(  dataStore: DataStore<Preferences>): SavedCityRepository {
        return SavedCityRepositoryImpl(dataStore)
    }
}