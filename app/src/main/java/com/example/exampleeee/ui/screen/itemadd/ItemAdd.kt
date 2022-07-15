package com.example.exampleeee.ui.screen.itemadd

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.exampleeee.ui.screen.itemadd.components.TextFieldForAll
import com.example.exampleeee.R
import com.example.exampleeee.data.InventoryApplication
import com.example.exampleeee.data.Item
import com.example.exampleeee.ui.screen.inventory.ForageableViewModel
import com.example.exampleeee.ui.screen.inventory.InventoryViewModelFactory

@Composable
fun ItemAddScreen(navController: NavController){
    val activity = LocalContext.current as Activity
    val focusManager = LocalFocusManager.current
    val viewModel: ForageableViewModel = viewModel(
        factory = InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    )
    var nameT by remember { mutableStateOf("")}
    var locationT by remember { mutableStateOf("")}
    var notesT by remember { mutableStateOf("")}
    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
    ) {

        TextFieldForAll(
            label = stringResource(id = R.string.name),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(
                        FocusDirection.Down
                    )
                }
            ),
            value = nameT,
            onValueChange = {nameT = it}
        )

        TextFieldForAll(
            label = stringResource(id = R.string.location),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(
                        FocusDirection.Down
                    )
                }
            ),
            value = locationT,
            onValueChange = {locationT = it}
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text( text = stringResource(id = R.string.app_name))
        }
        
        TextFieldForAll(
            label = stringResource(id = R.string.notes),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            value = notesT,
            onValueChange = {notesT = it}
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    addNewItem(
                        itemName = nameT,
                        itemAddress = locationT,
                        itemNotes = notesT,
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            ) {
                Text(text = "save")
            }
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = { }
            ) {
                Text(text = "delete")
            }
        }
        
    }
}


private fun addNewItem(
    itemName: String,
    itemAddress: String,
    itemNotes: String,
    navController: NavController,
    viewModel: ForageableViewModel
){
    if(isEntryValid(
            itemName = itemName,
            itemAddress = itemAddress,
            itemNotes = itemNotes,
            viewModel = viewModel)
    ) {
        viewModel.addNewItem(
            itemName = itemName,
            itemNotes = itemNotes,
            itemAddress = itemAddress
        )
        navController.popBackStack()
    }
}

private fun isEntryValid(
    itemName: String,
    itemAddress: String,
    itemNotes: String,
    viewModel: ForageableViewModel
): Boolean {
    return viewModel.isEntryValid(
        itemName = itemName,
        itemAddress = itemAddress,
        itemNotes = itemNotes
    )
}