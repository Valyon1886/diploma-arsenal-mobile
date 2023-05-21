package com.example.arsenalmobile

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.arsenalmobile.Navigation.Screen.MainScreen
import com.example.arsenalmobile.ui.theme.ArsenalMobileTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    lateinit var auth: FirebaseAuth
//    var imageUri: Image? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            MainScreen(auth, mainActivity = this@MainActivity)
        }
        Log.d("ID = ", "${auth.currentUser?.uid}")
    }

    fun signOut(){
        auth.signOut()
        finish()
    }
}