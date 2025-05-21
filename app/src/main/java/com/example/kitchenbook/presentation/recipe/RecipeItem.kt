package com.example.kitchenbook.presentation.recipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.kitchenbook.data.local.entity.CartRecipe
import com.example.kitchenbook.data.remote.dto.RecipeDto
import com.example.kitchenbook.viewmodel.CartViewModel

@Composable
fun RecipeItem(recipe: RecipeDto, onClick: () -> Unit) {
    val cartViewModel: CartViewModel = hiltViewModel()

    val cartRecipes by cartViewModel.cartRecipes.collectAsState()

    val isAdded = cartRecipes.any { it.id == recipe.id }

    Card(colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { onClick() }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = recipe.image,
                contentDescription = recipe.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = recipe.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 12.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Button(
                onClick = {
                    if (!isAdded) {
                        cartViewModel.addToCart(
                            CartRecipe(recipe.id, recipe.title, recipe.image)
                        )
                    }
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = if (isAdded) Color(0xFF4CAF50) else MaterialTheme.colorScheme.primary
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (isAdded) Icons.Default.Done else Icons.Default.Add,
                        contentDescription = "add to cart"
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = if (isAdded) "Added" else "Add to Cart")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}



