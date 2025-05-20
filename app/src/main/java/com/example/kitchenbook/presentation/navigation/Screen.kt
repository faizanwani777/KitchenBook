package com.example.kitchenbook.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector


//Note to mysef:Sealed classes are special type of class that can only be subclassed in the same file.
sealed class Screen(val route:String,val title:String,val icon:ImageVector){
    data object Home:Screen("home","Home", Icons.Default.Home)
    data object Cart:Screen("cart","Cart", Icons.Default.ShoppingCart)
    data object Profile:Screen("profile","Profile", Icons.Default.Person)
}