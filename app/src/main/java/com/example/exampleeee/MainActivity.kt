package com.example.exampleeee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.exampleeee.data.MyService
import com.example.exampleeee.ui.theme.ExampleeeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ExampleeeeTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            MyService.startService(context, "service start")
                        }
                    ) { Text(text = "start") }
                    Button(
                        onClick = {
                            MyService.stopService(context)
                        }
                    ){ Text(text = "stop") }

                }
            }

        }
    }

}