package com.naeemdev.weather.data.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
@Serializable
data class CloudinessModel(
    @SerialName("all") val cloudiness: Int
)