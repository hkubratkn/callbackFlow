package com.example.exampleeee.presentation.products

import com.example.exampleeee.core.Utils.Companion.printError
import com.example.exampleeee.presentation.navigation.Screen
import com.example.exampleeee.presentation.products.components.ProductCard
import com.example.exampleeee.presentation.products.components.ProductsTopBar
import com.example.exampleeee.presentation.products.components.ProgressBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@Composable
@InternalCoroutinesApi
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
fun ProductsScreen(
    navController: NavController,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            ProductsTopBar()
        }
    ) {
        val products = viewModel.products.collectAsLazyPagingItems()
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn {
                items(
                    items = products
                ) { product ->
                    product?.let {
                        ProductCard(
                            product = product,
                            onProductClick = {
                                val jsonProduct = Gson().toJson(product)
                                navController.navigate(Screen.ProductScreen.route + "/${jsonProduct}")
                            }
                        )
                    }
                }
            }

            products.apply {
                when {
                    loadState.refresh is Loading -> ProgressBar()
                    loadState.refresh is Error -> printError(products.loadState.refresh as Error)
                    loadState.append is Loading -> ProgressBar()
                    loadState.append is Error -> printError(products.loadState.append as Error)
                }
            }
        }
    }
}