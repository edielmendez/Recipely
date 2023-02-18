package com.ediel.mv.recipely.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ediel.mv.recipely.data.repository.RecipesDataSource
import com.ediel.mv.recipely.data.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecipesDataSource
): ViewModel() {
    private val _uiState = MutableLiveData<HomeUIState>()
    val uiState: LiveData<HomeUIState> = _uiState

    init {
        getRecipes()
    }

    private fun getRecipes(){
        viewModelScope.launch {
            _uiState.value = HomeUIState.Loading(true)
            val result = repository.getRecipes()
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