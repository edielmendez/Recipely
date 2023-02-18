package com.ediel.mv.recipely.ui.home

import com.ediel.mv.recipely.domain.models.Recipe

sealed class HomeUIState{
    data class Loading(val value: Boolean): HomeUIState()
    data class Success(val recipes: List<Recipe>): HomeUIState()
    data class Error(val error:String): HomeUIState()
}
