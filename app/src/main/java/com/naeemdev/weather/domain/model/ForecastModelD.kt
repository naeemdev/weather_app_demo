package com.naeemdev.weather.domain.model

data class ForecastModelD(
    val weatherList: List<ForecastWeatherModelD>,
    val cityDtoData: CityModelD
)
