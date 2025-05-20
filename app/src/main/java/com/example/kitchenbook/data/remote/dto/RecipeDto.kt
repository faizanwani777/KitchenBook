package com.example.kitchenbook.data.remote.dto

data class RecipeDto(
    val id: Int,
    val title: String,
    val image: String,
    val summary: String?
)

data class RecipeResponse(val recipes:List<RecipeDto>)
