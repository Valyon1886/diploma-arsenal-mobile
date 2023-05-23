package com.example.arsenalmobile.ViewModels.AddGame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arsenalmobile.ui.theme.BackColor
import com.example.arsenalmobile.ui.theme.BoxColor
import com.example.arsenalmobile.ui.theme.NavBarColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun dropListMode(): String{
    val listMode = listOf("CLASSIC_THIRD", "BASE_ON_BASE", "CHAOS", "CUSTOM")
    var expandedMenuMode by remember { mutableStateOf(false) }
    var chooseMode by remember { mutableStateOf("Выбор режима") }
    ExposedDropdownMenuBox(
        expanded = expandedMenuMode,
        onExpandedChange = { expandedMenuMode = it},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp).background(color = BoxColor)

    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = chooseMode,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon( expanded = expandedMenuMode) },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                unfocusedBorderColor = NavBarColor,
                focusedBorderColor = NavBarColor,
                cursorColor = Color.White,
                disabledTrailingIconColor = Color.White,
                trailingIconColor = Color.White
            ),
            )
        ExposedDropdownMenu(modifier = Modifier.fillMaxWidth().background(color = BackColor),expanded = expandedMenuMode, onDismissRequest = { expandedMenuMode = false }) {
            listMode.forEach{mode ->
                DropdownMenuItem(modifier = Modifier.fillMaxWidth(),
                    onClick = {
                    chooseMode = mode
                    expandedMenuMode = false
                }
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = mode
                    )
                }
            }
        }
    }
    return chooseMode
}