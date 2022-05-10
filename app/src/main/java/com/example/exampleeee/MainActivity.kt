package com.example.exampleeee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.exampleeee.ui.theme.ExampleeeeTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleeeeTheme {
                AskPer()
            }
        }
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
        Text(text="granded permission, OK")
    }
}



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