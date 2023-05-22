package com.example.arsenalmobile.ViewModels.Games

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arsenalmobile.Controllers.GameController
import com.example.arsenalmobile.Models.Game

@Composable
fun Description(game: Game?) {
    var colorActivity = if(game?.isActive == true) remember { Color.Green } else remember { Color.Red }
    Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
        Column(){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Статус: "+ if(game?.isActive == true) "места есть" else "мест нет",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Box(
                        modifier = Modifier
                            .background(color = colorActivity, shape = CircleShape)
                            .size(20.dp),
                    )
                }
                Divider(modifier = Modifier.padding(start = 2.dp, end = 2.dp, top = 10.dp), color = Color.White)
            }
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 10.dp, top = 5.dp)) {
                Text(
                    text = "Описание",
                    color = Color.Gray
                )
                game?.description?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

    }
}