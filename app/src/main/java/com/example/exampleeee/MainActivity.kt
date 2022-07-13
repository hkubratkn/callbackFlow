package com.example.exampleeee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exampleeee.ui.screen.SecondScreen
import com.example.exampleeee.ui.screen.navigation.Screen
import com.example.exampleeee.ui.screen.one.OneScreen
import com.example.exampleeee.ui.theme.ExampleeeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleeeeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.OneS.route
                ){
                    composable(route = Screen.OneS.route){OneScreen(navController)}
                    composable(route = Screen.SecondS.route){SecondScreen(navController)}
                }

            }
        }
    }
}