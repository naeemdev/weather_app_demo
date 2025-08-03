package com.naeemdev.weather.domain.model

data class CityModelD(
    val country: String,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long,
    val cityName: String,
    val coordinate: CoordModelD
)
