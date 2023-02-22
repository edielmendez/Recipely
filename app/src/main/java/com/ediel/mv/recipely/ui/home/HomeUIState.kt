package com.ediel.mv.recipely.ui.home

import com.ediel.mv.recipely.domain.models.Recipe

sealed class HomeUIState<out T>{
    class Loading(val isLoading: Boolean): HomeUIState<Nothing>()
    class Success<T>(val data: T): HomeUIState<T>()
    class Error(val message: String): HomeUIState<Nothing>()
}
