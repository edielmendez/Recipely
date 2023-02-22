package com.ediel.mv.recipely.domain

import com.ediel.mv.recipely.data.repository.RecipesRepository
import com.ediel.mv.recipely.data.test.FakeRecipes
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class GetRecipesUseCaseTest{

    @RelaxedMockK
    private lateinit var respository: RecipesRepository

    lateinit var getRecipesUseCase: GetRecipesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRecipesUseCase = GetRecipesUseCase(respository)
    }

    @Test
    fun `when the api return a success result then get values from api`() = runBlocking {
        //Given

        coEvery { respository.getRecipes() } returns Result.success(FakeRecipes.recipes)


        //When
        val response = getRecipesUseCase()

        //Then
        coVerify(exactly = 1) { respository.getRecipes() }
        assert(response.isSuccess)
        assert(response.getOrNull() == FakeRecipes.recipes)
    }

    @Test
    fun `when the api return a failure result then get message`() = runBlocking {
        //Given
        val errorMessage: String = "Error Messaage"
        coEvery { respository.getRecipes() } returns Result.failure(Exception(errorMessage))


        //When
        val response = getRecipesUseCase()

        //Then
        coVerify(exactly = 1) { respository.getRecipes() }
        assert(response.isFailure)
        assert(response.exceptionOrNull()?.message == errorMessage)
    }
}