package com.example.exampleeee

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.exampleeee.ui.theme.ExampleeeeTheme
import com.google.firebase.Timestamp
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleeeeTheme {
                FirestoreScreen()
            }
        }
    }

}

@Composable
fun FirestoreScreen(){
    val db = Firebase.firestore
    val context = LocalContext.current
    val currentUserId = "456852357951" /** in fact instead auth.currentUser!!.uid */
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
                val date = Timestamp.now()
                val email = "example@example.com" /** in fact instead auth.currentUser!!.email */
                val postHashMap = hashMapOf<String, Any>()
                postHashMap["date"] = date
                postHashMap["mail"] = email
                db.collection("example").document(currentUserId).set(postHashMap).addOnCompleteListener { task->
                    if (task.isSuccessful && task.isComplete){
                        Toast.makeText(context, "Button1 Completed", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener { ex->
                    Toast.makeText(context, ex.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        ){
            Text(text="write firestore")
        }
        Button(
            onClick = {
                val postHashMapSecond = hashMapOf<String, Any>()
                postHashMapSecond["capital"] = true
                db.collection("example").document(currentUserId).set(postHashMapSecond, SetOptions.merge()).addOnCompleteListener { task->
                    if (task.isSuccessful && task.isComplete){
                        Toast.makeText(context, "Button2 completed", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener { ex->
                    Toast.makeText(context, ex.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        ){
            Text(text="write exist document")
        }
        Button(
            onClick = {
                db.collection("example").document(currentUserId).addSnapshotListener { value, error ->
                    if (error != null){
                        Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
                    }else{
                        if (value != null){
                            if (value.exists()){
                                val date = value.get("mail") as String
                                Toast.makeText(context, date, Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
        ){
            Text(text="read document")
        }
        Button(
            onClick = {
                db.collection("example").document(currentUserId).delete().addOnCompleteListener { task->
                    if(task.isSuccessful){
                        Toast.makeText(context, "deleted", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener { ex->
                    Toast.makeText(context, ex.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        ){
            Text(text="delete document")
        }
    }

}

/**
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat.startActivityForResult
import coil.compose.rememberImagePainter
import com.example.exampleeee.ui.theme.ExampleeeeTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class MainActivity : ComponentActivity() {
    var secilenGorsel : Uri? = null
    var secilenBitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleeeeTheme {
                AskPer()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==2 && resultCode== Activity.RESULT_OK && data != null){
            secilenGorsel = data.data
            if (secilenGorsel != null){
                if (Build.VERSION.SDK_INT >= 28){
                    val source = ImageDecoder.createSource(this.contentResolver, secilenGorsel!!)
                    secilenBitmap = ImageDecoder.decodeBitmap(source)
                    /**imageView.setImageBitmap(secilenBitmap)*/
                    Toast.makeText(this, "image one", Toast.LENGTH_LONG).show()
                }else{
                    secilenBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, secilenGorsel)
                    /**imageView.setImageBitmap(secilenBitmap)*/
                    Toast.makeText(this, "image two", Toast.LENGTH_LONG).show()
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}



@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AskPer(){

    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.READ_EXTERNAL_STORAGE)

    PermissionRequired(
        permissionState = cameraPermissionState,
        permissionNotGrantedContent = {
            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                Text(text="Enable camera permission")
            }
        },
        permissionNotAvailableContent = {
            Text(text="please enable camera functionalty from the settings")
        }
    ) {
        TakePhoto()
    }
}

@Composable
fun TakePhoto(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
                val galeriIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)
            }
        ){
            Text(text="open galery")
        }
    }
}
*/

/**
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exampleeee.ui.theme.ExampleeeeTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    val auth = Firebase.auth
    val db = Firebase.firestore
    var startScreen = "auth_screen"

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            startScreen = "inside_screen"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleeeeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = startScreen
                ){
                    composable ("auth_screen"){ AuthScreen(navController=navController)}
                    composable ("inside_screen"){ InsideScreen(navController=navController)}
                }

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1){
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account : GoogleSignInAccount? = task.getResult(ApiException::class.java)
                account?.idToken.let{ token->
                    var credential = GoogleAuthProvider.getCredential(token, null)
                    auth.signInWithCredential(credential).addOnCompleteListener { task->
                        if (task.isSuccessful){
                            val user = auth.currentUser
                            val userUid = user.uid
                            val userEmail = user!!.email
                            val userPhoto = user!!.photoUrl
                            val
                            db.collection("User").document(userUid).add

                        }else{
                            Toast.makeText(this, "sucsesfail", Toast.LENGTH_LONG).show()
                        }
                    }.addOnFailureListener { ex->
                        Toast.makeText(this, ex.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: ApiException) {
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

}

@Composable
fun AuthScreen(navController: NavController){
    val auth = Firebase.auth
    val activity = (LocalContext.current) as Activity
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()

                val client = GoogleSignIn.getClient(activity, gso)
                val signInIntent: Intent =  client.signInIntent
                activity.startActivityForResult(signInIntent, 1)
            }
        ){
            Text(text="go to inside")
        }
    }
}

@Composable
fun InsideScreen(navController: NavController){
    val auth = Firebase.auth
    Column (
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text="inside")
        Button(
            onClick = {
                auth.signOut()
                navController.popBackStack()
                navController.navigate("auth_screen")
            }
        ){
            Text(text="log out")
        }
    }
}
*/