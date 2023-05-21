package com.example.arsenalmobile.ViewModels.Blaster

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.arsenalmobile.Models.Blaster

@Composable
fun CardImage(blaster: Blaster?) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        backgroundColor = Color.White
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = rememberAsyncImagePainter(model = blaster?.image), contentDescription = "Картинка бластера", Modifier.size(200.dp))
        }
    }
}