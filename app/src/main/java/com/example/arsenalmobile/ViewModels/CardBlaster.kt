package com.example.arsenalmobile.ViewModels

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.arsenalmobile.Models.Blaster
import com.example.arsenalmobile.Navigation.Routes
import com.example.arsenalmobile.R
import com.example.arsenalmobile.ui.theme.BackColor

@Composable
fun CardBlaster(item: Blaster, navController: NavController){
    Card(
        modifier = Modifier.padding(5.dp),
        backgroundColor = BackColor
    ) {
        Column() {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Картинка бластера",
                Modifier.size(30.dp)
            )
            item.blasterName?.let { Text(text = it) }
            Button(onClick = { navController.navigate("${Routes.InfoBlaster.route}/${item.id}") }) {
                Text(text = "Подробнее о модели")
            }
        }
    }
}