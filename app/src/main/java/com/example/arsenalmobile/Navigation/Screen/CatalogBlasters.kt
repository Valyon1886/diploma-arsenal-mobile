package com.example.arsenalmobile.Navigation.Screen

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.arsenalmobile.Controllers.BlasterController
import com.example.arsenalmobile.Controllers.UserController
import com.example.arsenalmobile.MainActivity
import com.example.arsenalmobile.Models.Blaster
import com.example.arsenalmobile.Models.User
import com.example.arsenalmobile.R
import com.example.arsenalmobile.ViewModels.CardBlaster
import com.example.arsenalmobile.ui.theme.BackColor
import com.google.firebase.auth.FirebaseAuth

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CatalogBlasters(
    userController: UserController,
    blasterController: BlasterController,
    auth: FirebaseAuth,
    mainActivity: MainActivity,
    user: User,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    var filterExpand by remember { mutableStateOf(false) }
    var categoriesExpand by remember { mutableStateOf(false) }
    var seriesExpand by remember { mutableStateOf(false) }
    var listBlaster by remember { mutableStateOf<List<Blaster>>(emptyList()) }
    var listBlasterFilter by remember { mutableStateOf<List<Blaster>>(emptyList()) }

    LaunchedEffect(true){
        listBlaster = blasterController.getBlasters()
        listBlasterFilter = listBlaster
    }

    var series: MutableList<String> = listBlaster
        .mapNotNull{blaster -> blaster.series}
        .distinct()
        .toMutableList()
    var categories: MutableList<String> = listBlaster
        .mapNotNull{blaster -> blaster.category}
        .distinct()
        .toMutableList()

    Log.d("series = ", "$series")

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        text = "КАТАЛОГ",
                        color = Color.Black
                    )
                },
                backgroundColor = Color.Yellow,
                actions = {
                    IconButton(onClick = {
                        filterExpand = !filterExpand
                        categoriesExpand = false
                        seriesExpand = false
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
                                .background(color = Color.White)
                                .fillMaxWidth()
                        ) {
                            val filterPointList = listOf<String>("Серия", "Категория")
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                content = {
                                    items(filterPointList) { item ->
                                        Text(
                                            text = item,
                                            modifier = Modifier
                                                .border(2.dp, Color.Red)
                                                .clickable {
                                                    if (item == "Серия") {
                                                        seriesExpand = true
                                                        categoriesExpand = false
                                                    }
                                                    if (item == "Категория") {
                                                        seriesExpand = false
                                                        categoriesExpand = true
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
                        AnimatedVisibility(visible = seriesExpand) {
                            Box(
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .fillMaxWidth()
                            ) {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(3),
                                    content = {
                                        items(series) { item ->
                                            Text(
                                                modifier = Modifier
                                                    .border(2.dp, Color.Red)
                                                    .clickable {
                                                        listBlasterFilter =
                                                            listBlaster.filter { it.series == item }
                                                        Log.d("filter ", "$listBlasterFilter")
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
                        AnimatedVisibility(visible = categoriesExpand) {
                            Box(
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .fillMaxWidth()
                            ) {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(2),
                                    content = {
                                        items(categories) { item ->
                                            Text(
                                                modifier = Modifier
                                                    .border(2.dp, Color.Red)
                                                    .clickable {
                                                        listBlasterFilter =
                                                            listBlaster.filter { it.category == item }
                                                        Log.d("filter ", "$listBlasterFilter")
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
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    content = {
                        items(listBlasterFilter) { item ->
                            CardBlaster(item, navController)
                        }
                    }
                )
            }
        }
    )
}

