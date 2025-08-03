package com.naeemdev.weather.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoordModel(
    @SerialName("lat") val latitude: Double,
    @SerialName("lon") val longitude: Double
)
