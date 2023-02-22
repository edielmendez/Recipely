package com.ediel.mv.recipely.domain.models

import com.ediel.mv.recipely.data.repository.RecipesRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val respository: RecipesRepository
) {
    suspend operator fun invoke() = respository.getRecipes()
}