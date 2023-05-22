package com.example.arsenalmobile.Screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.arsenalmobile.Controllers.BlasterController
import com.example.arsenalmobile.Controllers.UserController
import com.example.arsenalmobile.MainActivity
import com.example.arsenalmobile.Models.Blaster
import com.example.arsenalmobile.Models.User
import com.example.arsenalmobile.ViewModels.Blaster.CardImage
import com.example.arsenalmobile.ViewModels.Blaster.Description
import com.example.arsenalmobile.ui.theme.BackColor
import com.example.arsenalmobile.ui.theme.getColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun InfoBlaster(
    blasterId: Long,
    blasterController: BlasterController,
    navController: NavController,
    userController: UserController,
    user: User,
    mainActivity: MainActivity
) {
    var blaster by remember { mutableStateOf<Blaster?>(null) }
    LaunchedEffect(true) {
        blaster = blasterController.findBlaster(blasterId)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        text = "${blaster?.blasterName}",
                        color = Color.Black
                    )
                },
                backgroundColor = Color.Yellow
            )
        },
        content = {
            Column(

                modifier = Modifier.background(BackColor).fillMaxWidth().fillMaxHeight().padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CardImage(blaster)
                Description(blaster)
                blaster?.let { it1 -> getColor(it1) }
                    ?.let { it2 -> ButtonDefaults.buttonColors(it2) }?.let { it3 ->
                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth().padding(10.dp),
                            colors = it3
                        ) {
                            Text(text = "Добавить в арсенал")
                        }
                    }
            }

        }
    )
}
