package com.example.arsenalmobile.ViewModels.Blaster

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.arsenalmobile.Models.Blaster

@Composable
fun Description(blaster: Blaster?) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        backgroundColor = Color.White,

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Характеристика")
            Text(text = "${blaster?.author}")
        }
    }
}