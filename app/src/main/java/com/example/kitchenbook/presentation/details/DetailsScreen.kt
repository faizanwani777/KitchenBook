
package com.example.kitchenbook.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.kitchenbook.viewmodel.RecipeViewModel
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer

@Composable
fun DetailsScreen(recipeId: Int, viewModel: RecipeViewModel = hiltViewModel()) {
    val recipeDetails = viewModel.selectedRecipeDetails
    val isLoading = viewModel.isLoading
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View)

    LaunchedEffect(recipeId) {
        viewModel.getRecipeDetails(recipeId)
    }

    if (isLoading) {
        ShimmerDetailsScreen(shimmer = shimmer)
    } else {
        recipeDetails?.let { details ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Recipe Details",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    tonalElevation = 2.dp,
                    modifier = Modifier.fillMaxWidth().shadow(elevation = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), horizontalAlignment =Alignment.CenterHorizontally) {
                        Text(
                            text = "Total Cost: \$${details.totalCost}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Cost per Serving: \$${details.totalCostPerServing}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Ingredients",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                details.ingredients.forEach { ingredient ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation =8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            AsyncImage(
                                model = "https://spoonacular.com/cdn/ingredients_100x100/${ingredient.image}",
                                contentDescription = ingredient.name,
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )

                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = ingredient.name,
                                style = MaterialTheme.typography.titleMedium,
                                maxLines = 1, // ✅ Limit to single line
                                overflow = TextOverflow.Ellipsis, // ✅ Add ellipsis for overflow
                                modifier = Modifier
                                    .weight(1f) // ✅ Let it take available space without pushing right column
                                    .padding(end = 8.dp)
                            )

                            Column(horizontalAlignment = Alignment.End,
                                modifier = Modifier.widthIn(max = 140.dp)) {

                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Price: \$${ingredient.price}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Metric: ${ingredient.amount.metric.value} ${ingredient.amount.metric.unit}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "US: ${ingredient.amount.us.value} ${ingredient.amount.us.unit}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                    }
                }



                Spacer(modifier = Modifier.height(40.dp))
                Card {Spacer(modifier = Modifier.height(40.dp))  }
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Recipe not found")
        }
    }
}
