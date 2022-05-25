package com.example.exampleeee.presentation.products.components

import com.example.exampleeee.core.Constants.PRODUCTS_SCREEN
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun ProductsTopBar() {
    TopAppBar (
        title = {
            Text(
                text = PRODUCTS_SCREEN
            )
        }
    )
}