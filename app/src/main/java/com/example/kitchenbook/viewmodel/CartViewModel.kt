package com.example.kitchenbook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitchenbook.data.local.entity.CartRecipe
import com.example.kitchenbook.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {
    val cartRecipes = repository.getAll().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    fun addToCart(recipe: CartRecipe) {
        viewModelScope.launch { repository.add(recipe) }
    }
    fun removeFromCart(recipe: CartRecipe) {
        viewModelScope.launch { repository.remove(recipe) }
    }




}