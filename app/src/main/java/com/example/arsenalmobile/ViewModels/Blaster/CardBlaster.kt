package com.example.arsenalmobile.ViewModels

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.arsenalmobile.Models.Blaster
import com.example.arsenalmobile.Navigation.Routes
import com.example.arsenalmobile.R
import com.example.arsenalmobile.ui.theme.BackColor

@Composable
fun CardBlaster(item: Blaster, navController: NavController){
    Card(
        modifier = Modifier.padding(5.dp),
        backgroundColor = Color.White,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberAsyncImagePainter(model = item.image),
                contentDescription = "Картинка бластера",
                Modifier.size(100.dp)
            )
            item.blasterName?.let {
                Text(text = it)
            }
            Button(
                modifier = Modifier
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(Color.Red),
                onClick = { navController.navigate("${Routes.InfoBlaster.route}/${item.id}") }
            ) {
                Text(
                    text = "Подробнее о модели",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}