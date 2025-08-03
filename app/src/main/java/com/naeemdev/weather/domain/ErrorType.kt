package com.naeemdev.weather.domain

import com.naeemdev.weather.R


enum class ErrorType(val errorMessageResId: Int) {
    NO_INTERNET(R.string.error_no_internet),
    FORBIDDEN(R.string.error_forbidden),
    UNAUTHORIZED(R.string.error_unauthorized),
    UNKNOWN(R.string.error_unknown),
    INTERNAL_SERVER_ERROR(R.string.error_internal_server),
    IO_EXCEPTION(R.string.error_io_exception),
    EMPTY_RESPONSE(R.string.sorry_no_data_found),
}