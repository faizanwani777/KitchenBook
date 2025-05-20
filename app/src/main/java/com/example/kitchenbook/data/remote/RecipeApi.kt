package com.example.kitchenbook.data.remote

import com.example.kitchenbook.data.remote.dto.RecipeDetailsResponse
import com.example.kitchenbook.data.remote.dto.RecipeResponse
import com.example.kitchenbook.data.remote.dto.SearchRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int=10
    ): RecipeResponse

    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int = 5
    ): SearchRecipeResponse


    @GET("recipes/{id}/priceBreakdownWidget.json")
    suspend fun getRecipeDetailsById(
    @Path("id") id: Int,
    @Query("apiKey") apiKey: String,
    ): RecipeDetailsResponse
}