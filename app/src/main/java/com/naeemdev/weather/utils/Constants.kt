package com.naeemdev.weather.utils

object Constants {
    const val BASE_URL: String = "https://api.openweathermap.org"
    const val API_KEY: String = "1e5abc2d10bee17ed30178d9cf85a1f2"
    const val UNITS: String = "metric"
    const val FORECAST_END_POINT = "/data/2.5/forecast"
    const val SAVED_CITY_NAME_KEY = "selected_city"
}


object WeatherConditions {
    const val CLEAR_SKY = "clear sky"
    const val FEW_CLOUDS = "few clouds"
    const val SCATTERED_CLOUDS = "scattered clouds"
    const val BROKEN_CLOUDS = "broken clouds"
    const val SHOWER_RAIN = "shower rain"
    const val RAIN = "rain"
    const val THUNDERSTORM = "thunderstorm"
    const val SNOW = "snow"
    const val MIST = "mist"
}

object MainWeatherConditions {
    const val CLOUDS = "Clouds"
    const val SNOW = "Snow"
    const val RAIN = "Rain"
    const val THUNDERSTORM = "Thunderstorm"
    const val CLEAR = "Clear"
}