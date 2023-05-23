package com.example.arsenalmobile.Screen

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.arsenalmobile.Controllers.GameController
import com.example.arsenalmobile.Controllers.UserController
import com.example.arsenalmobile.MainActivity
import com.example.arsenalmobile.Models.Blaster
import com.example.arsenalmobile.Models.Game
import com.example.arsenalmobile.Models.User
import com.example.arsenalmobile.Navigation.Routes
import com.example.arsenalmobile.R
import com.example.arsenalmobile.ViewModels.Games.CardGame
import com.example.arsenalmobile.ui.theme.BackColor
import com.example.arsenalmobile.ui.theme.BoxColor
import com.example.arsenalmobile.ui.theme.NavBarColor
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
    var filterExpand by remember { mutableStateOf(false) }
    var modsExpand by remember { mutableStateOf(false) }
    var activesExpand by remember { mutableStateOf(false) }

    var listGamesFilter by remember { mutableStateOf<List<Game>>(emptyList()) }

    var mods: MutableList<String> = listGames
        .map { game -> game.mode}
        .distinct()
        .toMutableList()

    LaunchedEffect(true){
        listGames = gameController.getGames()
        listGamesFilter = listGames
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
                backgroundColor = NavBarColor,
                actions = {
                    IconButton(onClick = {
                        filterExpand = !filterExpand
                        modsExpand = false
                        activesExpand = false
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_tune_24),
                            contentDescription = "Сортировка",
                            tint = Color.Black
                        )
                    }
                },
            )
        },
        content = {
            Column(modifier = Modifier.background(color = BackColor)){
                AnimatedVisibility(visible = filterExpand) {
                    Column(){
                        Box(
                            modifier = Modifier
                                .background(color = BackColor)
                                .fillMaxWidth()
                        ) {
                            val filterPointList = listOf("Активность", "Режим")
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                content = {
                                    items(filterPointList) { item ->
                                        Text(
                                            text = item,
                                            modifier = Modifier
                                                .border(2.dp, Color.DarkGray)
                                                .clickable {
                                                    if (item == "Активность") {
                                                        activesExpand = true
                                                        modsExpand = false
                                                    }
                                                    if (item == "Режим") {
                                                        activesExpand = false
                                                        modsExpand = true
                                                    }
                                                },
                                            color = Color.Black,
                                            fontSize = 15.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            )
                        }
                        AnimatedVisibility(visible = activesExpand) {
                            Box(
                                modifier = Modifier
                                    .background(color = BackColor)
                                    .fillMaxWidth()
                            ) {
                                val filterPointList = listOf("Открытые", "Закрытые")
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(2),
                                    content = {
                                        items(filterPointList) { item ->
                                            Text(
                                                text = item,
                                                modifier = Modifier
                                                    .border(2.dp, Color.DarkGray)
                                                    .clickable {
                                                        if (item == "Открытые") {
                                                            listGamesFilter =
                                                                listGames.filter { it.isActive }
                                                        }
                                                        if (item == "Закрытые") {
                                                            listGamesFilter =
                                                                listGames.filter { !it.isActive }
                                                        }
                                                    },
                                                color = Color.Black,
                                                fontSize = 15.sp,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                )
                            }
                        }
                        AnimatedVisibility(visible = modsExpand) {
                            Box(
                                modifier = Modifier
                                    .background(color = BackColor)
                                    .fillMaxWidth()
                            ) {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(2),
                                    content = {
                                        items(mods) { item ->
                                            Text(
                                                modifier = Modifier
                                                    .border(2.dp, Color.DarkGray)
                                                    .clickable {
                                                        listGamesFilter =
                                                            listGames.filter { it.mode == item }
                                                    },
                                                text = item,
                                                color = Color.Black,
                                                fontSize = 15.sp,
                                                textAlign = TextAlign.Center,
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier
                    .padding(10.dp)
                    .background(color = BoxColor, shape = RoundedCornerShape(10.dp))
                    ){
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Добавить событие", color = Color.White, fontSize = 20.sp,)
                        IconButton(
                            modifier = Modifier
                                        .background(color = NavBarColor)
                                        .size(30.dp),
                            onClick = {
                                navController.navigate(Routes.CreateGame.route)
                            }
                        ) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "Добавить событие",
                                tint = Color.Black
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    modifier = Modifier.padding(bottom = 55.dp)
                ) {
                    items(listGamesFilter) { game ->
                        CardGame(game, navController)
                    }
                }
            }
        }
    )
}