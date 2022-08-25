package com.example.exampleeee

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exampleeee.Constants.CHAT_COLLECTION
import com.example.exampleeee.Constants.DATE_FIELD
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatScreen()
        }
    }
}

@Composable
private fun ChatScreen(
    chatScreenViewModel: ChatScreenViewModel = viewModel(
        factory = ChatScreenViewModelFactory(
            chatId = "it",
            chatScreenRepository = ChatScreenRepository()
        )
    )
) {

    val chatList = chatScreenViewModel.getChat.collectAsState().value
    Log.d("chat", chatList.toString())

}

class ChatScreenViewModel(
    private val chatId: String,
    private val chatScreenRepository: ChatScreenRepository
): ViewModel(){

    private val _getChat = MutableStateFlow<List<Chat>>(emptyList())
    var getChat: StateFlow<List<Chat>> = _getChat

    init {
        viewModelScope.launch {
            chatScreenRepository.getChatList(chatId)
                .collect { items ->
                    _getChat.value = items
                }
        }
    }

}

class ChatScreenViewModelFactory(
    private val chatId: String,
    private val chatScreenRepository: ChatScreenRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ChatScreenViewModel(chatId, chatScreenRepository) as T
}

class ChatScreenRepository() {

    fun getChatList(chatId: String): Flow<List<Chat>> {
        return Firebase.firestore.collection(CHAT_COLLECTION)
            .document(chatId)
            .collection(chatId)
            .orderBy(DATE_FIELD, Query.Direction.ASCENDING)
            .limit(10)
            .snapshotFlow()
            .map { querySnapshot ->
                querySnapshot.documents.map {
                    it.toObject(Chat::class.java)!!
                }
            }
    }

    private fun Query.snapshotFlow(): Flow<QuerySnapshot> = callbackFlow {
        val listenerRegistration = addSnapshotListener { value, error ->
            if (error != null) {
                close()
                return@addSnapshotListener
            }
            if (value != null) {
                trySend(value)
            }
        }
        awaitClose {
            listenerRegistration.remove()
        }
    }
}


data class Chat(
    var text: String ?= null,
    var who: String ?= null,
    @ServerTimestamp
    var date: Date? = null
)
