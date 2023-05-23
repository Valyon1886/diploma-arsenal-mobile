package com.example.arsenalmobile.Screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.arsenalmobile.R
import com.example.arsenalmobile.ui.theme.BackColor

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
fun UserTest() {
    TestScreen()
}

@Composable
fun TestScreen() {
    Column(
        modifier = Modifier.background(BackColor).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = R.mipmap.ic_launcher_new), // Замените на свою ресурсную картинку
            contentDescription = "Test Image",
//                contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
        ) {
            Text(
                text = "Вопрос:",
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Текст вашего вопроса",
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { /* Обработка выбора первого варианта ответа */ },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Первый вариант",
                    style = MaterialTheme.typography.button
                )
            }
            Button(
                onClick = { /* Обработка выбора второго варианта ответа */ },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Второй вариант",
                    style = MaterialTheme.typography.button
                )
            }
            Button(
                onClick = { /* Обработка выбора третьего варианта ответа */ },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Третий вариант",
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}