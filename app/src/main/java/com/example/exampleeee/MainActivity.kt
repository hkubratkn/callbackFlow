package com.example.exampleeee

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgs
import androidx.navigation.navArgument
import kotlinx.parcelize.Parcelize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            val sharedViewModel: SharedViewModel = viewModel()

            NavHost(
                navController = navController,
                startDestination = Screen.OneS.route
            ) {
                composable(route = Screen.OneS.route) {
                    OneScreen(navController = navController, sharedViewModel = sharedViewModel)}
                composable(route = Screen.SecondS.route){
                    SecondScreen(navController = navController, sharedViewModel = sharedViewModel)
                }
            }

        }
    }
}

@Composable
fun OneScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    Column(modifier = Modifier.fillMaxSize()){
        Text(text = "screen_one")
        Button(
            onClick = {
                val person = Person(firstName = "ffif", lastName = "llsl")
                sharedViewModel.addPerson(person)
                navController.navigate(Screen.SecondS.route)
            }
        ){Text(text = "go")}
    }
}

@Composable
fun SecondScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val person = sharedViewModel.person
    LaunchedEffect(key1 = person ) {
        if (person != null) {
            Log.d("eede", "${person.lastName}")
            Log.d("eede", "${person.firstName}")
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "second_screen")
        Button(
            onClick = {
                navController.navigate(Screen.OneS.route)
            }
        ){
            Text(text = "back")
        }
    }
}

class SharedViewModel: ViewModel() {

    var person by mutableStateOf<Person?>(null)
        private set

    fun addPerson(newPerson: Person) {
        person = newPerson
    }
}


sealed class Screen(val route: String) {
    object OneS: Screen(route = "one_scren")
    object SecondS: Screen(route = "second_screen")
}

@Parcelize
class Person(
    val firstName: String,
    val lastName: String
): Parcelable