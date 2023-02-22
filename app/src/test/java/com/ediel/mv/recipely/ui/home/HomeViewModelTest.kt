package com.ediel.mv.recipely.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ediel.mv.recipely.data.test.FakeRecipes
import com.ediel.mv.recipely.domain.GetRecipesUseCase
import com.ediel.mv.recipely.domain.models.Recipe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class HomeViewModelTest{
    @RelaxedMockK
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    private lateinit var quoteViewModel: HomeViewModel

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        quoteViewModel = HomeViewModel(useCase = getRecipesUseCase)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time and get all recipes`() = runTest(){
        //Given

        coEvery { getRecipesUseCase() } returns Result.success(FakeRecipes.recipes)
        //When
        quoteViewModel.getRecipes()
        //Then
        assert(quoteViewModel.uiState.value is HomeUIState.Success)
        assert((quoteViewModel.uiState.value as HomeUIState.Success).data == FakeRecipes.recipes)
    }

    @Test
    fun `when viewmodel calls get recipes and get failure`() = runTest(){
        //Given
        val errorMessage = "Error Message"
        coEvery { getRecipesUseCase() } returns Result.failure(Exception(errorMessage))
        //When
        quoteViewModel.getRecipes()
        //Then
        assert(quoteViewModel.uiState.value is HomeUIState.Error)
        assert((quoteViewModel.uiState.value as HomeUIState.Error).message == errorMessage)
        //coVerify(exactly = 1) { quoteViewModel.getRecipes() }
    }
}