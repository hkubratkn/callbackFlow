package com.example.exampleeee.ui.screen.itemDetail

import android.app.Activity
import androidx.compose.animation.core.StartOffsetType.Companion.Delay
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.exampleeee.R
import com.example.exampleeee.data.InventoryApplication
import com.example.exampleeee.data.Item
import com.example.exampleeee.ui.screen.inventory.ForageableViewModel
import com.example.exampleeee.ui.screen.inventory.InventoryViewModelFactory
import com.example.exampleeee.ui.screen.navigation.Screen

@Composable
fun ItemDetailScreen(
    selectedId: Long,
    navController: NavController
) {
    val activity = LocalContext.current as Activity
    val viewModel: ForageableViewModel = viewModel(
        factory = InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    )
    val updateItem = Item(
        id = 0,
        itemName = "name",
        itemAddress = "address",
        itemInSeason = false,
        itemNotes = "notes"
    )
    val item = viewModel.getForegeable(selectedId).collectAsState(initial = updateItem)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)){
        Text(text = item.value.id.toString())
        Text(text = item.value.itemAddress)
        Text(text = item.value.itemNotes)
        Text(text = item.value.itemName)
        Text(text = item.value.itemInSeason.toString())

        Button(
            modifier = Modifier.padding(top = 50.dp),
            onClick = {
                viewModel.deleteItem(item.value)
                navController.navigate(Screen.ItemS.route)
            }
        ){
            Text(text = "delete")
        }
        
    }
}