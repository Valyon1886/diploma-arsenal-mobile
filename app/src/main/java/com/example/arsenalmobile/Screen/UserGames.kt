package com.example.arsenalmobile.Screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.arsenalmobile.Controllers.GameController
import com.example.arsenalmobile.Controllers.UserController
import com.example.arsenalmobile.MainActivity
import com.example.arsenalmobile.Models.Game
import com.example.arsenalmobile.Models.User
import com.example.arsenalmobile.ViewModels.Games.CardGame
import com.example.arsenalmobile.ui.theme.NavBarColor
import com.google.firebase.auth.FirebaseAuth

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserGames(
    userId: Long,
    userController: UserController,
    gameController: GameController,
    mainActivity: MainActivity,
    navController: NavController
) {
    var user by remember { mutableStateOf<User?>(null) }
    LaunchedEffect(Unit){
        user = userController.findUser(userId)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        text = "Мои столкновения",
                        color = Color.Black
                    )
                },
                backgroundColor = NavBarColor,
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier.padding(bottom = 55.dp)
            ){
                user?.let { user ->
                    items(user.games){ game ->
                        CardGame(game, navController)
                    }
                }
            }
        }
    )
}