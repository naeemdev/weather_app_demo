package com.naeemdev.weather.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naeemdev.weather.R
import com.naeemdev.weather.domain.Resource
import com.naeemdev.weather.domain.usecases.GetCityName
import com.naeemdev.weather.domain.usecases.GetForecastWithCityNameUseCase
import com.naeemdev.weather.domain.usecases.SaveCityName
import com.naeemdev.weather.presentation.SearchCityState.*
import com.naeemdev.weather.utils.AppCoroutineDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val getForecastWithCityName: GetForecastWithCityNameUseCase,
    private val dispatchersProvider: AppCoroutineDispatchers,
    private val getCityName : GetCityName,
    private val saveCityName : SaveCityName,
) : ViewModel() {

    private val _searchCityState = MutableStateFlow<SearchCityState>(Idle)
    val searchCityState = _searchCityState.asStateFlow()


    var searchFieldValue by mutableStateOf("")
        private set

    var isCitySearched by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch(dispatchersProvider.io) {
            if (getCityName().isNotEmpty()) {
                searchFieldValue = getCityName()
                isCitySearched = true
                fetchForecastWithCityName(searchFieldValue)
            }
        }
    }
    fun searchCityClick() {
        isCitySearched = true
        viewModelScope.launch(dispatchersProvider.io) {

            if (checkSearchFieldValue()) {
                _searchCityState.value = Loading
                saveCityName(searchFieldValue)
                fetchForecastWithCityName(searchFieldValue)
            } else {
                _searchCityState.value = Error(R.string.empty_field_error)
            }
        }
    }


    private suspend fun fetchForecastWithCityName(cityName: String) {

        when (val result = getForecastWithCityName(cityName)) {
            is Resource.Success -> {
                _searchCityState.value = Success(result.data)
            }

            is Resource.Error -> {
                _searchCityState.value = Error(result.errorType.errorMessageResId)
            }
        }
    }


    private fun checkSearchFieldValue(): Boolean {
        return searchFieldValue.isNotEmpty()
    }

    fun updateSearchField(input: String) {
        searchFieldValue = input
    }

}