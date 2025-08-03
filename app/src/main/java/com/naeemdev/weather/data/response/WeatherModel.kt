package com.naeemdev.weather.data.response

import kotlinx.serialization.Serializable

@Serializable
data class WeatherModel(
    val main: String,
    val description: String
)
