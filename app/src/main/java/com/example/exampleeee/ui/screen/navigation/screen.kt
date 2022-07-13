package com.example.exampleeee.ui.screen.navigation

sealed class Screen(val route: String){
    object OneS: Screen(route = "one_screen")
    object SecondS: Screen(route = "second_screen")
}