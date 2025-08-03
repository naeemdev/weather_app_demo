package com.naeemdev.weather.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.naeemdev.weather.R
import com.naeemdev.weather.domain.model.ForecastModelD
import com.naeemdev.weather.presentation.component.CityWeatherCard
import com.naeemdev.weather.presentation.component.ErrorCard
import com.naeemdev.weather.ui.theme.WeatherTheme
import com.naeemdev.weather.utils.HourConverter
import com.naeemdev.weather.utils.WeatherType

@Composable
fun SearchCityScreen() {
    val viewModel: SearchCityViewModel = hiltViewModel()
    val searchCityState by viewModel.searchCityState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
            .padding(16.dp),
        topBar = {
            SearchField(
                searchFieldValue = viewModel.searchFieldValue,
                updateSearchField = viewModel::updateSearchField,
                searchCityClick = viewModel::searchCityClick
            )
        }
    ) { innerPadding ->

        SearchCityScreenContent(
            modifier = Modifier.padding(innerPadding),
            searchCityState = searchCityState,
            cityName = viewModel.searchFieldValue
        )
    }

}

@Composable
fun SearchCityScreenContent(
    modifier: Modifier = Modifier,
    searchCityState: SearchCityState,
    cityName: String
) {
    Column(
        modifier = modifier
    ) {
        when (searchCityState) {
            is SearchCityState.Loading -> {
                Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(top = 16.dp),
                    )
                }
            }

            is SearchCityState.Success -> {
                if (searchCityState.forecast != null) {
                    WantedCityWeatherSection(searchCityState.forecast, cityName)
                }
            }

            is SearchCityState.Error -> {
                SearchResultErrorMessage(
                    searchCityState.errorMessage ?: R.string.an_unknown_error_occurred
                )
            }

            SearchCityState.Idle -> {
                //no op
            }
        }
    }
}

@Composable
private fun WantedCityWeatherSection(forecast: ForecastModelD,
                                     cityName: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.search_result_for, cityName),
            style = MaterialTheme.typography.titleMedium
        )
        CityWeatherCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            degree = "${forecast.weatherList[0].weatherData.temp.toInt()}",
            city = forecast.cityDtoData.cityName.ifEmpty { cityName },
            country = forecast.cityDtoData.country,
            description = forecast.weatherList[0].weatherStatus[0].description,
            weatherImage = WeatherType.setWeatherType(
                forecast.weatherList[0].weatherStatus[0].mainDescription,
                forecast.weatherList[0].weatherStatus[0].description,
                HourConverter.convertHour(forecast.weatherList[0].date.substring(11, 13)),
            ),
        )
    }
}

@Composable
private fun SearchResultErrorMessage(errorMessage: Int) {
    ErrorCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        errorTitle = stringResource(R.string.oooops),
        errorDescription = stringResource(errorMessage),
    )
}


@Composable
private fun SearchField(
    searchFieldValue: String,
    updateSearchField: (String) -> Unit,
    searchCityClick: () -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchFieldValue,
        onValueChange = { updateSearchField(it) },
        label = {
            Text(text = stringResource(R.string.please_enter_city_name))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = { searchCityClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_search_24),
                    contentDescription = null
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SearchCityScreenContentPreview() {
    WeatherTheme {
        SearchCityScreenContent(
            searchCityState = SearchCityState.Loading,
            cityName = "Islamabad"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchFieldPreview() {
    WeatherTheme {
        SearchField(
            searchFieldValue = "",
            updateSearchField = {},
            searchCityClick = {}
        )
    }
}