package com.example.kitchenbook.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_recipes")
data class CartRecipe(
    @PrimaryKey val id: Int,
    val title: String,
    val image: String
    )