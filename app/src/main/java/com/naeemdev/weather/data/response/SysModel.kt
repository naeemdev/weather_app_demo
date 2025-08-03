package com.naeemdev.weather.data.response

import kotlinx.serialization.Serializable

@Serializable
data class SysModel(
    val sunrise: Long,
    val sunset: Long,
)