package com.naeemdev.weather.data.response

import kotlinx.serialization.Serializable

@Serializable
data class CityModel(
    val country: String,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long,
    val cityName: String?,
    val coordinate: CoordModel?
)
