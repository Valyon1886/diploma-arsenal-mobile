package com.example.arsenalmobile.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.arsenalmobile.Controllers.GameController
import com.example.arsenalmobile.Controllers.UserController
import com.example.arsenalmobile.MainActivity
import com.example.arsenalmobile.Models.Game
import com.example.arsenalmobile.Models.User
import com.example.arsenalmobile.ViewModels.Games.Description
import com.example.arsenalmobile.ViewModels.Games.DropListUser
import com.example.arsenalmobile.ui.theme.BoxColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InfoGame(
    gameId: Long,
    gameController: GameController,
    navController: NavController,
    userController: UserController,
    user: User,
    mainActivity: MainActivity
){
    var game by remember { mutableStateOf<Game?>(null) }
    LaunchedEffect(true) {
        game = gameController.findGame(gameId)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        text = "${game?.name}",
                        color = Color.Black
                    )
                },
                backgroundColor = Color.Yellow
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = BoxColor),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.padding(15.dp)){
                    Image(
                        painter = rememberAsyncImagePainter(model = game?.image),
                        contentDescription = "Картинка игры",
                        Modifier.size(150.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Description(game)
                DropListUser(gameId, gameController)
                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            if((user.id != null) && (game != null)){
                                gameController.addUserToGame(game!!, user.id!!)
                                game = gameController.findGame(gameId)
                                if (game?.users?.count() == game?.amount) game?.let { gameController.endActive(it) }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Откликнуться")
                }
            }
        }
    )
}