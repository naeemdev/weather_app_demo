package com.naeemdev.weather.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastWeatherModel(
    @SerialName("main") val weatherData: MainModel,
    @SerialName("weather") val weatherStatus: List<WeatherModel>,
    @SerialName("wind") val wind: WindModel,
    @SerialName("dt_txt") val date: String,
    @SerialName("clouds") val cloudinessDto: CloudinessModel
)
