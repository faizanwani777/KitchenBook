package com.example.kitchenbook.presentation.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kitchenbook.ui.theme.KitchenBookTheme
import com.example.kitchenbook.viewmodel.RecipeViewModel
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: RecipeViewModel = hiltViewModel()) {
    val recipes = viewModel.recipes
    val isLoading = viewModel.isLoading
    val searchResults = viewModel.searchResults

    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View)


    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                if (isSearching) {
                    SearchBar(text = searchText, onTextChange = {
                        searchText = it
                        viewModel.searchRecipes(it)
                    })
                } else {
                    Text("Recipes", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }

            },
            actions = {
                IconButton(onClick = {
                    isSearching = !isSearching
                    if (!isSearching) {
                        searchText = ""
                    }
                }) {
                    Icon(
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp),
                        imageVector = if (isSearching) Icons.Default.Close else Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            },
            modifier = Modifier.shadow(8.dp),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when {
                isLoading -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        items(5) {
                            ShimmerRecipeItem(shimmerInstance)
                        }
                    }
                }

                isSearching -> {
                    if (searchResults.isEmpty()) {
                        Text(
                            text = "No search results", modifier = Modifier.align(Alignment.Center)
                        )

                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .navigationBarsPadding()
                                .padding(innerPadding)
                        ) {
                            items(searchResults) { recipe ->
                                SearchRecipeItemfunc(recipe) {
                                    navController.navigate("details/${recipe.id}")
                                }
                            }
                        }
                    }


                }

                !isSearching && recipes.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .padding(innerPadding)
                    ) {
                        items(recipes) { recipe ->
                            RecipeItem(recipe, onClick = {
                                navController.navigate("details/${recipe.id}")
                            })
                        }

                        item {
                            Spacer(modifier = Modifier.height(70.dp))
                        }
                    }
                }

                else -> {
                    Text(
                        text = "No recipes found", modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    KitchenBookTheme {
        HomeScreen(navController = rememberNavController())
    }
}
