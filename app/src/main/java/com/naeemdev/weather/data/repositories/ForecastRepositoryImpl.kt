package com.naeemdev.weather.data.repositories



import com.naeemdev.weather.data.mapper.ForecastDtoMapper
import com.naeemdev.weather.data.remote.WeatherApi
import com.naeemdev.weather.domain.ErrorHandler.handleApiError
import com.naeemdev.weather.domain.ErrorHandler.handleIOError
import com.naeemdev.weather.domain.ErrorHandler.handleNetworkError
import com.naeemdev.weather.domain.ErrorHandler.handleUnknownError
import com.naeemdev.weather.domain.ErrorType
import com.naeemdev.weather.domain.Resource
import com.naeemdev.weather.domain.model.ForecastModelD
import com.naeemdev.weather.domain.repositories.ForecastRepository
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : ForecastRepository {

    override suspend fun getForecastDataWithCityName(cityName: String): Resource<ForecastModelD> {
        return try {
            val response = weatherApi.getForecastDataWithCityName(cityName)

            if (response.isSuccessful) {
                val dto = response.body()
                if (dto != null) {
                    Resource.Success(ForecastDtoMapper.mapFromEntity(dto))
                } else {
                    Resource.Error(ErrorType.EMPTY_RESPONSE, response.code())
                }
            } else {
                response.handleApiError()
            }

        } catch (e: CancellationException) {
            throw e
        } catch (e: UnknownHostException) {
            e.handleNetworkError()
        } catch (e: IOException) {
            e.handleIOError()
        } catch (e: Exception) {
            e.handleUnknownError()
        }
    }
}