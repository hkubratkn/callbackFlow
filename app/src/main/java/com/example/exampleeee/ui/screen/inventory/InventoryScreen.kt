package com.example.exampleeee.ui.screen.inventory

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import com.example.exampleeee.data.InventoryApplication
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.exampleeee.data.Item
import com.example.exampleeee.ui.screen.navigation.Screen
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemScreen(navController: NavController) {
    val activity = LocalContext.current as Activity
    val viewModel: ForageableViewModel = viewModel(
        factory = InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    )
    val list = viewModel.allItems.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.ItemAddS.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        },
        content = {
            LazyColumn {
                items(list.value) { er ->
                    Card(list = er, navController = navController)
                }
            }
        }
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Card(list: Item, navController: NavController){
    val id = list.id
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.Magenta),
        onClick = {
            navController.navigate("${Screen.ItemDetailS.route}/$id")
        }
    ) {
        Column {
            Text(text = list.itemName)
            Text(text = list.itemAddress)
        }
    }
}