package com.example.exampleeee.presentation.product.components

import com.example.exampleeee.core.Constants.PRODUCT_SCREEN
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi

@Composable
@InternalCoroutinesApi
fun ProductTopBar(
    navController: NavController
) {
    TopAppBar (
        title = {
            Text(
                text = PRODUCT_SCREEN
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = null,
                )
            }
        }
    )
}