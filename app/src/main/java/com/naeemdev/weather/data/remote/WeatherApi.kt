package com.naeemdev.weather.data.remote


import com.naeemdev.weather.data.response.ForecastModel
import com.naeemdev.weather.utils.Constants.API_KEY
import com.naeemdev.weather.utils.Constants.FORECAST_END_POINT
import com.naeemdev.weather.utils.Constants.UNITS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(FORECAST_END_POINT)
    suspend fun getForecastDataWithCityName(
        @Query("q") cityName: String,
        @Query("APPID") apiKey: String = API_KEY,
        @Query("units") units: String = UNITS,
    ): Response<ForecastModel>
}