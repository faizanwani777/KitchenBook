package com.example.kitchenbook.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kitchenbook.data.local.entity.CartRecipe
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(recipe: CartRecipe)

    @Delete
    suspend fun removeFromCart(recipe: CartRecipe)

    @Query("SELECT * FROM cart_recipes")
    fun getCartRecipes(): Flow<List<CartRecipe>>
}