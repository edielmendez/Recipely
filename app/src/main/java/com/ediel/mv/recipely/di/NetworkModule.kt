package com.ediel.mv.recipely.di

import com.ediel.mv.recipely.data.api.RecipeService
import com.ediel.mv.recipely.data.repository.RecipesDataSource
import com.ediel.mv.recipely.data.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRecipeService(): RecipeService{
        return RecipeService.create()
    }

    @Provides
    @Singleton
    fun provideRecipesRepository(dataSource: RecipesDataSource): RecipesRepository = dataSource
}