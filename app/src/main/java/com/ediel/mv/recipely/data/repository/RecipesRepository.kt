package com.ediel.mv.recipely.data.repository

import com.ediel.mv.recipely.domain.models.Recipe

interface RecipesRepository {
    suspend fun getRecipes(): Result<List<Recipe>>
}