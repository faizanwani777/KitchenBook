package com.example.kitchenbook.presentation.Cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kitchenbook.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, viewModel: CartViewModel = hiltViewModel()) {
    val cartRecipes by viewModel.cartRecipes.collectAsState()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "Cart", fontSize = 24.sp, fontWeight = FontWeight.Bold
                )
            },
            modifier = Modifier.shadow(8.dp),
        )
    }) { padding ->
        if (cartRecipes.isEmpty()) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding), contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Your cart is empty...", color = Color.Black
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(padding)
            ) {
                items(cartRecipes) { recipe ->
                    CartItem(
                        recipe = recipe, onRemove = { viewModel.removeFromCart(recipe) },
                        onClick = {navController.navigate("details/${recipe.id}")}
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(70.dp))
                }
            }
        }
    }

}