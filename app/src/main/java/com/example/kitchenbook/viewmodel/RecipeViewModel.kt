package com.example.kitchenbook.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitchenbook.data.remote.dto.RecipeDetailsResponse
import com.example.kitchenbook.data.remote.dto.RecipeDto
import com.example.kitchenbook.data.remote.dto.SearchRecipeItem
import com.example.kitchenbook.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository,
) : ViewModel() {

    var recipes by mutableStateOf<List<RecipeDto>>(emptyList())
        private set


    var isLoading by mutableStateOf(false)
        private set

    var searchResults by mutableStateOf<List<SearchRecipeItem>>(emptyList())

    private val apiKey = "c6a49f9442054abc8647eb22dc0d8399"

    var selectedRecipeDetails by mutableStateOf<RecipeDetailsResponse?>(null)

    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            isLoading = true
            recipes = repository.getRandomRecipes(apiKey)
            isLoading = false
        }
    }

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            isLoading = true
            searchResults = repository.searchRecipes(query, apiKey)
            isLoading = false
        }
    }

    fun getRecipeDetails(id: Int) {
        viewModelScope.launch {
            isLoading = true

            try {
                selectedRecipeDetails = repository.getRecipeDetailsById(id, apiKey)
            } catch (e: Exception) {
                selectedRecipeDetails = null
            }
            isLoading = false
        }
    }
}