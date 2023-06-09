package com.example.arsenalmobile.ViewModels.Games

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.arsenalmobile.Controllers.GameController
import com.example.arsenalmobile.Models.Game
import com.example.arsenalmobile.ui.theme.NavBarColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropListUser(gameId: Long, gameController: GameController){
    var listUser by remember { mutableStateOf<List<String>>(emptyList()) }
    var expandedMenuUser by remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        listUser = gameController.getUserNames(gameId)
    }
    ExposedDropdownMenuBox(
        expanded = expandedMenuUser,
        onExpandedChange = { expandedMenuUser = it}
    ) {
        TextField(
            value = "Список участников",
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMenuUser) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                unfocusedBorderColor = NavBarColor,
                focusedBorderColor = NavBarColor,
                cursorColor = Color.White,
                disabledTrailingIconColor = Color.White,
                trailingIconColor = Color.White
            ),

        )
        ExposedDropdownMenu(expanded = expandedMenuUser, onDismissRequest = { expandedMenuUser = false }) {
            listUser.forEach{user ->
                DropdownMenuItem(onClick = {}) {
                    Text(text = user)
                }
            }
        }
    }
}