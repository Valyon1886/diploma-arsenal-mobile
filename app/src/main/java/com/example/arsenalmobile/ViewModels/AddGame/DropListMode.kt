package com.example.arsenalmobile.ViewModels.AddGame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun dropListMode(): String{
    val listMode = listOf("CLASSIC_THIRD", "BASE_ON_BASE", "CHAOS", "CUSTOM")
    var expandedMenuMode by remember { mutableStateOf(false) }
    var chooseMode by remember { mutableStateOf("Выбор режима") }
    ExposedDropdownMenuBox(
        expanded = expandedMenuMode,
        onExpandedChange = { expandedMenuMode = it},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp).background(color = Color.White)
    ) {
        TextField(
            value = chooseMode,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMenuMode) },

            )
        ExposedDropdownMenu(expanded = expandedMenuMode, onDismissRequest = { expandedMenuMode = false }) {
            listMode.forEach{mode ->
                DropdownMenuItem(onClick = {
                    chooseMode = mode
                    expandedMenuMode = false
                }) {
                    Text(
                        text = mode
                    )
                }
            }
        }
    }
    return chooseMode
}