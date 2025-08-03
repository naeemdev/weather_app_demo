package com.naeemdev.weather.data.mapper


import com.naeemdev.weather.data.response.ForecastModel
import com.naeemdev.weather.domain.model.CityModelD
import com.naeemdev.weather.domain.model.CloudinessModelD
import com.naeemdev.weather.domain.model.CoordModelD
import com.naeemdev.weather.domain.model.ForecastModelD
import com.naeemdev.weather.domain.model.ForecastWeatherModelD
import com.naeemdev.weather.domain.model.MainModelD
import com.naeemdev.weather.domain.model.WeatherModelD
import com.naeemdev.weather.domain.model.WindModelD

object ForecastDtoMapper {
    fun mapFromEntity(entity: ForecastModel): ForecastModelD {
        val forecastWeather: List<ForecastWeatherModelD> = entity.weatherList.map {
            ForecastWeatherModelD(
                weatherData = MainModelD(
                    it.weatherData.temp,
                    it.weatherData.feelsLike,
                    it.weatherData.pressure,
                    it.weatherData.humidity
                ),
                weatherStatus = listOf(
                    WeatherModelD(it.weatherStatus[0].main, it.weatherStatus[0].description)
                ),
                wind = WindModelD(it.wind.speed),
                date = it.date,
                cloudiness = CloudinessModelD(it.cloudinessDto.cloudiness)
            )
        }

        return ForecastModelD(
            forecastWeather,
            CityModelD(
                entity.cityDtoData.country,
                entity.cityDtoData.timezone,
                entity.cityDtoData.sunrise,
                entity.cityDtoData.sunset,
                entity.cityDtoData.cityName ?: "",
                CoordModelD(
                    entity.cityDtoData.coordinate?.latitude ?: 0.0,
                    entity.cityDtoData.coordinate?.longitude ?: 0.0
                )
            )
        )
    }
}