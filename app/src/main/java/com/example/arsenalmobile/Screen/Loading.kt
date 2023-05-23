package com.example.arsenalmobile.Screen

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.arsenalmobile.R
import com.example.arsenalmobile.ui.theme.BackColor
import com.example.arsenalmobile.ui.theme.LoadColor
import com.example.arsenalmobile.ui.theme.MegaColor

@Composable
@Preview
fun Loading() {
    val load = "Обновление "
    val progressValue = 2f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,animationSpec = infiniteRepeatable(animation = tween(3000))
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(BackColor).fillMaxSize()
    ) {
        Column( horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberAsyncImagePainter(model = R.mipmap.ic_launcher_new), // Замените на свою ресурсную картинку
                contentDescription = "Test Image",
//                contentScale = ContentScale.FillWidth
                modifier = Modifier
                    .size(125.dp)
                    .clip(CircleShape)
                    .border(4.dp, color = Color.Black, CircleShape)
            )
            Spacer(modifier = Modifier.height(height = 30.dp))
            Text(
                text = load,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(height = 10.dp))
            LinearProgressIndicator(
                modifier = Modifier
                    .background(LoadColor)
                    .height(8.dp),
                color = MegaColor
            )
        }
    }
}