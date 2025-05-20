package com.example.kitchenbook.data.remote.dto

data class SearchRecipeItem (
    val id:Int,
    val title:String,
    val image:String
)
data class SearchRecipeResponse(val results:List<SearchRecipeItem>)