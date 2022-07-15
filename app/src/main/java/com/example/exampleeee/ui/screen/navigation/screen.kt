package com.example.exampleeee.ui.screen.navigation

sealed class Screen(val route: String){
    object ItemS: Screen(route = "item_screen")
    object ItemDetailS: Screen(route = "item_detail_screen")
    object ItemAddS: Screen(route = "item_add_screen")
}