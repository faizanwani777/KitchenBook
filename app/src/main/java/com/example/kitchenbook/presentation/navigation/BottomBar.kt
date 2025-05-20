package com.example.kitchenbook.presentation.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(Screen.Home, Screen.Cart, Screen.Profile)
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Surface(
        color = Color.White,
        tonalElevation = 0.dp,
        shadowElevation = 20.dp,
        shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp),
        modifier = Modifier
    ) {
        NavigationBar(
            containerColor = Color.White,
            tonalElevation = 0.dp
        ) {
            items.forEach { screen ->
                val selected = currentRoute == screen.route
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title,
                            tint = if (selected) MaterialTheme.colorScheme.primary else Color.Black
                        )
                    },
                    label = {
                        Text(
                            text = screen.title,
                            color = if (selected) MaterialTheme.colorScheme.primary else Color.Black
                        )
                    },
                )
            }
        }
    }
}
