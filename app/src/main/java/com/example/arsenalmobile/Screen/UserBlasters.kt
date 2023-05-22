package com.example.arsenalmobile.Screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.arsenalmobile.Controllers.BlasterController
import com.example.arsenalmobile.Controllers.UserController
import com.example.arsenalmobile.MainActivity
import com.example.arsenalmobile.Models.User
import com.example.arsenalmobile.R
import com.example.arsenalmobile.ViewModels.CardBlaster
import com.example.arsenalmobile.ui.theme.BackColor
import com.example.arsenalmobile.ui.theme.NavBarColor

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserBlasters(
    userId: Long,
    userController: UserController,
    blasterController: BlasterController,
    mainActivity: MainActivity,
    navController: NavController
) {
    var user by remember { mutableStateOf<User?>(null) }
    LaunchedEffect(Unit){
        user = userController.findUser(userId)
    }

    Scaffold (
        backgroundColor = BackColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        text = "Мой инвентарь",
                        color = Color.Black
                    )
                },
                backgroundColor = NavBarColor,
            )
        },
        content = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                    user?.let { user ->
                        items(user.arsenal) { item ->
                            CardBlaster(item, navController)
                        }
                    }
                }
            )
        }
    )
}