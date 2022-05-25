package com.example.exampleeee.presentation.navigation

import com.example.exampleeee.core.Constants.PRODUCTS_SCREEN
import com.example.exampleeee.core.Constants.PRODUCT_SCREEN

sealed class Screen(val route: String) {
    object ProductsScreen: Screen(PRODUCTS_SCREEN)
    object ProductScreen: Screen(PRODUCT_SCREEN)
}