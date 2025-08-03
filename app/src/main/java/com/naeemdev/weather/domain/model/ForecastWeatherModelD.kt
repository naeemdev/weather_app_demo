package com.naeemdev.weather.domain.model

data class ForecastWeatherModelD(
    val id: Int = 1,
    val weatherData: MainModelD,
    val weatherStatus: List<WeatherModelD>,
    val wind: WindModelD,
    val date: String,
    val cloudiness: CloudinessModelD
)
