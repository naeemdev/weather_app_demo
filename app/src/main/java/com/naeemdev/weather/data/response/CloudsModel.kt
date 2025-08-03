package com.naeemdev.weather.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CloudsModel(
    @SerialName("all") val cloudiness: Int
)
