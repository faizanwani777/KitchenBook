package com.example.kitchenbook

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.kitchenbook.presentation.navigation.BottomBar
import com.example.kitchenbook.presentation.navigation.NavigationGraph
import com.example.kitchenbook.ui.theme.KitchenBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KitchenBookTheme {
//                note_to_self:rememberNavController() is Compose function that remembers the controller even when the screen recomposes.
               val navController= rememberNavController()
                Scaffold (bottomBar = { BottomBar(navController) }){
                    NavigationGraph(navController=navController)
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    KitchenBookTheme {
//    }
//}