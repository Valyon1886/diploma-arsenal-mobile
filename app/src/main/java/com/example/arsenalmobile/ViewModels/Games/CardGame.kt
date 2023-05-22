package com.example.arsenalmobile.ViewModels.Games

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.arsenalmobile.Controllers.GameController
import com.example.arsenalmobile.Models.Game
import com.example.arsenalmobile.Models.User
import com.example.arsenalmobile.Navigation.Routes
import com.example.arsenalmobile.ui.theme.BoxColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CardGame(game: Game, navController: NavController) {
    var colorActivity = if(game.isActive) remember { Color.Green } else remember { Color.Red }
    Card(
        modifier = Modifier.padding(10.dp),
        backgroundColor = BoxColor,
        shape = RoundedCornerShape(15.dp)
    ) {

        Column(){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = game.name,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Divider(modifier = Modifier.padding(5.dp), color = Color.White)

            }
            Row(Modifier.fillMaxWidth().padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(5.dp)) {
                    Text(
                        text = "Описание",
                        color = Color.Gray
                    )
                    Text(
                        text = game.description,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Column(horizontalAlignment = Alignment.End,){
                    Box(
                        modifier = Modifier
                            .background(color = colorActivity, shape = CircleShape)
                            .size(20.dp),
                    )
                }

            }
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    border = BorderStroke(3.dp, Color.Black),
                    contentPadding = PaddingValues(8.dp),
                    shape = RoundedCornerShape(15.dp),
                    onClick = {
                        navController.navigate("${Routes.InfoGame.route}/${game.id}")
                    }
                ) {
                    Text(text = "Подробнее")
                }
            }
        }
    }
}