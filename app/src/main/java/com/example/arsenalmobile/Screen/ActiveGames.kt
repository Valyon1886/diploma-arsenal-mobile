package com.example.arsenalmobile.Screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.arsenalmobile.Controllers.UserController
import com.example.arsenalmobile.MainActivity
import com.example.arsenalmobile.Models.User
import com.google.firebase.auth.FirebaseAuth

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ActiveGames(
    userController: UserController,
    auth: FirebaseAuth,
    mainActivity: MainActivity,
    user: User,
    navController: NavController
) {

}