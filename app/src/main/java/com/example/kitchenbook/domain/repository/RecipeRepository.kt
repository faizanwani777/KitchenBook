package com.example.kitchenbook.domain.repository

import com.example.kitchenbook.data.remote.RecipeApi
import com.example.kitchenbook.data.remote.dto.RecipeDetailsResponse
import com.example.kitchenbook.data.remote.dto.RecipeDto
import com.example.kitchenbook.data.remote.dto.SearchRecipeItem
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val api: RecipeApi,
) {
    suspend fun getRandomRecipes(apiKey: String, number: Int = 10): List<RecipeDto> {
        return try {
            api.getRandomRecipes(apiKey, number).recipes
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun searchRecipes(query: String, apiKey: String): List<SearchRecipeItem> {
        return try {
            api.searchRecipes(query, apiKey).results
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getRecipeDetailsById(id: Int, apiKey: String): RecipeDetailsResponse {
        return api.getRecipeDetailsById(id, apiKey)
    }
}