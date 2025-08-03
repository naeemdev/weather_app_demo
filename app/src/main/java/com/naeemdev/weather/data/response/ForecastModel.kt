package com.naeemdev.weather.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class ForecastModel(
    @SerialName("list") val weatherList: List<ForecastWeatherModel>,
    @SerialName("city") val cityDtoData: CityModel
)
