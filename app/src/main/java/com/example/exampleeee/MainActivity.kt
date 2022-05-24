package com.example.exampleeee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.exampleeee.ui.theme.ExampleeeeTheme
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleeeeTheme {
                val viewModel : MyDenemeViewModel by viewModels()
                MyDenemeScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MyDenemeScreen(viewModel : MyDenemeViewModel){

    val liveDataValue = viewModel.liveData.observeAsState()
    val stateFlowValue = viewModel.stateFlow.collectAsState()
    val sharedFlowValue = viewModel.sharedFlow.collectAsState(initial = "")

    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text= liveDataValue.value ?: "")
            Button(
                onClick = {viewModel.changeLiveDataValue()}
            ) {
                Text(text = "Live Data")
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Text(text= stateFlowValue.value ?: "")
            Button(
                onClick = {viewModel.changeStateFlowValue()}
            ) {
                Text(text = "State Flow")
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Text(text= sharedFlowValue.value ?: "")
            Button(
                onClick = {viewModel.changeSharedFlowValue()}
            ) {
                Text(text = "Shared Flow")
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

class MyDenemeViewModel : ViewModel(){

    private val _liveData = MutableLiveData<String>("LiveData")
    val liveData : LiveData<String> = _liveData

    fun changeLiveDataValue() {
        _liveData.value = "new value for live data"
    }


    private val _stateFlow = MutableStateFlow("StateFlow")
    val stateFlow = _stateFlow.asStateFlow()

    fun changeStateFlowValue(){
        _stateFlow.value = "new value state flow"
    }

    /**sharedflow is better than stateflow*/
    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun changeSharedFlowValue() {
        viewModelScope.launch {
            _sharedFlow.emit("new value of shared flow")
        }
    }
}