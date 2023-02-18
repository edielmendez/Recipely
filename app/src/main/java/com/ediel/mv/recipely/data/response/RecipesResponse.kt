package com.ediel.mv.recipely.data.response

import com.ediel.mv.recipely.data.models.RecipeDTO
import com.google.gson.annotations.SerializedName

data class RecipesResponse(
    @SerializedName("recipes") val recipes: List<RecipeDTO>
)
