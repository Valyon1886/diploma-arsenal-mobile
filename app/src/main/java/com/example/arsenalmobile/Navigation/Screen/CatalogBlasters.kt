package com.example.arsenalmobile.Navigation.Screen

import android.annotation.SuppressLint
import android.graphics.drawable.shapes.Shape
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.arsenalmobile.Controllers.BlasterController
import com.example.arsenalmobile.Controllers.UserController
import com.example.arsenalmobile.MainActivity
import com.example.arsenalmobile.Models.Blaster
import com.example.arsenalmobile.Models.User
import com.example.arsenalmobile.Navigation.Routes
import com.example.arsenalmobile.R
import com.example.arsenalmobile.ViewModels.CardBlaster
import com.example.arsenalmobile.ui.theme.BackColor
import com.example.arsenalmobile.ui.theme.BoxColor
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

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
    var settingExpand by remember { mutableStateOf(false) }
    var listBlaster by remember { mutableStateOf<List<Blaster>>(emptyList()) }

    LaunchedEffect(true){
        listBlaster = blasterController.getBlasters()
    }

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
                backgroundColor = BackColor,
                actions = {
                    IconButton(onClick = {
                        settingExpand = !settingExpand
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
            Column(){
                AnimatedVisibility(visible = settingExpand) {
                    Box(modifier = Modifier.background(color = BoxColor)) {
                        Row() {
                            Text(text = "Серия")
                            Text(text = "Патроны")
                        }
                    }
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    content = {
                        items(listBlaster) { item ->
                            CardBlaster(item, navController)
                        }
                    }
                )
            }
        }
    )
}

