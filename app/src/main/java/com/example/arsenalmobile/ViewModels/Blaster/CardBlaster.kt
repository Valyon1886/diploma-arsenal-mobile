package com.example.arsenalmobile.ViewModels

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.arsenalmobile.Models.Blaster
import com.example.arsenalmobile.Navigation.Routes
import com.example.arsenalmobile.R
import com.example.arsenalmobile.ui.theme.BackColor
import com.example.arsenalmobile.ui.theme.EliteColor
import com.example.arsenalmobile.ui.theme.MegaColor
import com.example.arsenalmobile.ui.theme.RivalColor
import com.example.arsenalmobile.ui.theme.ZombieColor
import com.example.arsenalmobile.ui.theme.getColor

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
                colors = ButtonDefaults.buttonColors(getColor(item)),
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