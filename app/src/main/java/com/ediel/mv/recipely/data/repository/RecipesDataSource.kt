package com.ediel.mv.recipely.data.repository

import com.ediel.mv.recipely.data.api.RecipeService
import com.ediel.mv.recipely.data.models.RecipeDTO
import com.ediel.mv.recipely.data.response.RecipesResponse
import com.ediel.mv.recipely.domain.models.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class RecipesDataSource @Inject constructor(
    private val service: RecipeService
): RecipesRepository  {
    override suspend fun getRecipes() = withContext(Dispatchers.IO) {
        try {
            val response = service.getRecipes()
            if(response.isSuccessful){
                Result.success(response.body()?.mapToRecipe() ?: emptyList() )
            }else{
                Result.failure(Exception(response.errorBody()?.string()))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    private fun RecipesResponse.mapToRecipe(): List<Recipe>{
        return this.recipes.map {
            Recipe(
                name = it.name ?: "",
                image = it.image ?: "",
                description = it.description ?: ""
            )
        }
    }

}