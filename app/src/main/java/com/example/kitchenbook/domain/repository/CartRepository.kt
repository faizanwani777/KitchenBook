package com.example.kitchenbook.domain.repository

import com.example.kitchenbook.data.local.dao.CartDao
import com.example.kitchenbook.data.local.entity.CartRecipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartDao: CartDao) {
    suspend fun add(recipe: CartRecipe){
        cartDao.addToCart(recipe)
    }
    suspend fun remove(recipe: CartRecipe){
        cartDao.removeFromCart(recipe)
    }
     fun getAll(): Flow<List<CartRecipe>> {
        return  cartDao.getCartRecipes()
    }

}