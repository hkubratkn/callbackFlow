package com.example.exampleeee.ui.screen

import android.app.Activity
import com.example.exampleeee.data.InventoryApplication
import com.example.exampleeee.data.Item
import com.example.exampleeee.ui.screen.inventory.InventoryViewModel
import com.example.exampleeee.ui.screen.inventory.InventoryViewModelFactory
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
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
import com.example.exampleeee.R

@Composable
fun SecondScreen(navController: NavController){
    var nameText by remember { mutableStateOf("")}
    var priceText by remember { mutableStateOf("")}
    var stockText by remember { mutableStateOf("")}
    val focusManager = LocalFocusManager.current
    val activity = LocalContext.current as Activity
    lateinit var item: Item

    val viewModel: InventoryViewModel = viewModel(
        factory = InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(32.dp)
    ) {
        Text(text = stringResource(id = R.string.app_name))
        TextFieldForAll(
            value = nameText,
            label = "name",
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
            onValueChange = {nameText = it}
        )
        TextFieldForAll(
            value = priceText,
            label = "price",
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
            onValueChange = {priceText = it}
        )
        TextFieldForAll(
            value = stockText,
            label = "stock",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            onValueChange = {stockText = it}
        )

        Button(
            onClick = {
                addNewItem(nameText,priceText,stockText,navController,viewModel)
            }
        ) {
            Text(text = "save")
        }

    }
    
}

private fun addNewItem(
    nameText: String,
    priceText: String,
    stockText: String,
    navController: NavController,
    viewModel: InventoryViewModel
){
    if(isEntryValid(nameText,priceText,stockText,viewModel)) {
        viewModel.addNewItem(
            nameText,priceText,stockText
        )
        navController.popBackStack()
    }
}

private fun isEntryValid(
    nameText: String,
    priceText: String,
    stockText: String,
    viewModel: InventoryViewModel
): Boolean {
    return viewModel.isEntryValid(
        nameText,priceText,stockText
    )
}



@Composable
fun TextFieldForAll(
    value: String,
    label: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    onValueChange: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(text = label)},
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}
