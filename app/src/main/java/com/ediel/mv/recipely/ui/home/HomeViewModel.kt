package com.ediel.mv.recipely.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ediel.mv.recipely.domain.GetRecipesUseCase
import com.ediel.mv.recipely.domain.models.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetRecipesUseCase
): ViewModel() {
    private val _uiState = MutableLiveData<HomeUIState<List<Recipe>>>()
    val uiState: LiveData<HomeUIState<List<Recipe>>> = _uiState

    var selectedRecipe: Recipe? = null

    init {
        getRecipes()
    }

    fun getRecipes(){
        viewModelScope.launch {
            _uiState.value = HomeUIState.Loading(true)
            val result = useCase()
            _uiState.value = HomeUIState.Loading(false)
            result.onSuccess {
                _uiState.value = HomeUIState.Success(it)
            }
            result.onFailure {
                _uiState.value = HomeUIState.Error(it.message.toString())
            }
        }
    }
}