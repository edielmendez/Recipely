package com.ediel.mv.recipely.ui.home

import com.ediel.mv.recipely.domain.models.Recipe

sealed class HomeUIState<T>(
    val isLoading: Boolean? = null,
    val data: T? = null,
    val message: String? = null
){
    class Loading<T>(value: Boolean): HomeUIState<T>(isLoading = value)
    class Success<T>(data: T): HomeUIState<T>(data = data)
    class Error<T>(message: String?): HomeUIState<T>(message = message)
}
