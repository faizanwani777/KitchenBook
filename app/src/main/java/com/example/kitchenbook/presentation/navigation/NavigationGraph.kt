package com.example.kitchenbook.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kitchenbook.presentation.Cart.CartScreen
import com.example.kitchenbook.presentation.details.DetailsScreen
import com.example.kitchenbook.presentation.profile.ProfileScreen
import com.example.kitchenbook.presentation.recipe.HomeScreen


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Cart.route) { CartScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(
            "details/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {backStackEntry->
            val id= backStackEntry.arguments?.getInt("id") ?:0
            DetailsScreen(recipeId=id)
        }
    }
}