package com.naeemdev.weather.repository

import com.naeemdev.weather.data.mapper.ForecastDtoMapper
import com.naeemdev.weather.data.remote.WeatherApi
import com.naeemdev.weather.data.repositories.ForecastRepositoryImpl
import com.naeemdev.weather.data.response.CityModel
import com.naeemdev.weather.data.response.CoordModel
import com.naeemdev.weather.data.response.ForecastModel
import com.naeemdev.weather.domain.ErrorType
import com.naeemdev.weather.domain.Resource
import com.naeemdev.weather.domain.model.CityModelD
import com.naeemdev.weather.domain.model.CoordModelD
import com.naeemdev.weather.domain.model.ForecastModelD

import io.mockk.coEvery
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

class ForecastRepositoryImpl {
    @get:Rule
    val mockkRule = MockKRule(this)
    private lateinit var repository: ForecastRepositoryImpl
    private val apiService = mockk<WeatherApi>()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockkObject(ForecastDtoMapper)
        repository = ForecastRepositoryImpl(apiService)
    }

    @Test
    fun `getForecastDataWithCityName returns success`() = runTest {
        val forecastModel = ForecastModel(
            cityDtoData = CityModel(
                country = "Pakistan",
                timezone = 5,
                sunrise = 0L,
                sunset = 0L,
                cityName = "Islamabad",
                coordinate = CoordModel(latitude = 0.0, longitude = 0.0)
            ),
            weatherList = listOf(),
        )
        val mappedForecast = ForecastModelD(
            cityDtoData = CityModelD(
                country = "Pakistan",
                timezone = 5,
                sunrise = 0L,
                sunset = 0L,
                cityName = "Islamabad",
                coordinate = CoordModelD(latitude = 0.0, longitude = 0.0)
            ),
            weatherList = listOf(),
        )

        val successfulResponse = mockk<Response<ForecastModel>> {
            every { isSuccessful } returns true
            every { body() } returns forecastModel
            every { code() } returns 200
        }

        coEvery { apiService.getForecastDataWithCityName("Islamabad") } returns successfulResponse
        every { ForecastDtoMapper.mapFromEntity(forecastModel) } returns mappedForecast

        val result = repository.getForecastDataWithCityName("Islamabad")
        assertTrue(result is Resource.Success)
        assertEquals("Islamabad", (result as Resource.Success).data.cityDtoData.cityName)
    }

    @Test
    fun `getForecastDataWithCityName returns 401 unauthorized`() = runTest {
        val response = mockk<Response<ForecastModel>> {
            every { isSuccessful } returns false
            every { code() } returns 401
            every { message() } returns "Unauthorized"
        }

        coEvery { apiService.getForecastDataWithCityName("Islamabad") } returns response

        val result = repository.getForecastDataWithCityName("Islamabad")

        assertTrue(result is Resource.Error)
        assertEquals(ErrorType.UNAUTHORIZED, (result as Resource.Error).errorType)
        assertEquals(401, result.statusCode)
    }
    @Test
    fun `getForecastDataWithCityName returns empty response`() = runTest {
        val response = mockk<Response<ForecastModel>> {
            every { isSuccessful } returns true
            every { body() } returns null
            every { code() } returns 200
        }

        coEvery { apiService.getForecastDataWithCityName("Islamabad") } returns response

        val result = repository.getForecastDataWithCityName("Islamabad")

        assertTrue(result is Resource.Error)
        assertEquals(ErrorType.EMPTY_RESPONSE, (result as Resource.Error).errorType)
        assertEquals(200, result.statusCode)
    }

    @Test
    fun `getForecastDataWithCityName handles UnknownHostException`() = runTest {
        coEvery { apiService.getForecastDataWithCityName("Islamabad") } throws UnknownHostException()

        val result = repository.getForecastDataWithCityName("Islamabad")

        assertTrue(result is Resource.Error)
        assertEquals(ErrorType.NO_INTERNET, (result as Resource.Error).errorType)
    }

    @Test
    fun `getForecastDataWithCityName handles IOException`() = runTest {
        coEvery { apiService.getForecastDataWithCityName("Islamabad") } throws IOException()

        val result = repository.getForecastDataWithCityName("Islamabad")

        assertTrue(result is Resource.Error)
        assertEquals(ErrorType.IO_EXCEPTION, (result as Resource.Error).errorType)
    }

    @Test
    fun `getForecastDataWithCityName handles unknown exception`() = runTest {
        coEvery { apiService.getForecastDataWithCityName("Islamabad") } throws RuntimeException("Something went wrong")

        val result = repository.getForecastDataWithCityName("Islamabad")

        assertTrue(result is Resource.Error)
        assertEquals(ErrorType.UNKNOWN, (result as Resource.Error).errorType)
    }
}