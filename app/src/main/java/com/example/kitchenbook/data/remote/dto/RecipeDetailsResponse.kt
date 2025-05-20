package com.example.kitchenbook.data.remote.dto

data class RecipeDetailsResponse(
    val ingredients: List<Ingredient>,
    val totalCost: Double,
    val totalCostPerServing: Double,
)

data class Ingredient(
    val name: String,
    val image: String,
    val price: Double,
    val amount: Amount,
)

data class Amount(
    val metric: UnitAmount,
    val us: UnitAmount,
)

data class UnitAmount(
    val value: Double,
    val unit: String,
)