package com.example.exampleeee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.exampleeee.ui.screen.inventory.ItemScreen
import com.example.exampleeee.ui.screen.itemDetail.ItemDetailScreen
import com.example.exampleeee.ui.screen.itemadd.ItemAddScreen
import com.example.exampleeee.ui.screen.navigation.Screen
import com.example.exampleeee.ui.theme.ExampleeeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleeeeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.ItemS.route
                ){
                    composable(route = Screen.ItemS.route) { ItemScreen(navController = navController)}
                    composable(
                        route = Screen.ItemDetailS.route + "/{id}",
                        arguments = listOf(navArgument("id"){ type = NavType.LongType})
                    ) {
                        ItemDetailScreen(
                            selectedId = it.arguments?.getLong("id") ?: -1,
                            navController = navController
                        )
                    }
                    composable(route = Screen.ItemAddS.route) { ItemAddScreen(navController = navController)}
                }
            }
        }
    }
}