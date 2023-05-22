package com.example.arsenalmobile.Screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
import com.google.firebase.auth.FirebaseAuth

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ActiveGames(
    userController: UserController,
    gameController: GameController,
    auth: FirebaseAuth,
    mainActivity: MainActivity,
    user: User,
    navController: NavController
) {
    var listGames by remember { mutableStateOf<List<Game>>(emptyList()) }

    LaunchedEffect(true){
        listGames = gameController.getGames()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        text = "Активные столкновения",
                        color = Color.Black
                    )
                },
                backgroundColor = Color.Yellow,
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier.padding(bottom = 55.dp)
            ){
                items(listGames){game ->
                    CardGame(game, navController)
                }
            }
        }
    )
}