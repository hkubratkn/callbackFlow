package com.example.exampleeee.ui.screen.one

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.exampleeee.ui.screen.navigation.Screen

@Composable
fun OneScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize()){
        Button(
            onClick = {
                navController.navigate(Screen.SecondS.route)
            }
        ){
            Text(text = "Add")
        }
    }
}