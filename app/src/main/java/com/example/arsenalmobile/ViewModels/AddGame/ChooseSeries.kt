package com.example.arsenalmobile.ViewModels.AddGame

import android.util.Log
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
fun chooseSeries(): MutableState<List<String>> {
    var seriesChoose = remember { mutableStateOf<List<String>>(emptyList()) }
    val listSeries = listOf("ZOMBIE_STRIKE", "ELITE", "NSTRIKE",  "RIVAL",  "MEGA")
    var expandedMenuSeries by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expandedMenuSeries,
        onExpandedChange = { expandedMenuSeries = it},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .background(color = Color.White)
    ) {
        TextField(
            value = if(seriesChoose.value.isEmpty()) "Серии бластеров" else seriesChoose.value.joinToString(", "),
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMenuSeries) },

            )
        ExposedDropdownMenu(expanded = expandedMenuSeries, onDismissRequest = { expandedMenuSeries = false }) {

            listSeries.forEach{series ->
                DropdownMenuItem(
                    onClick = {

                    }
                ) {
                    Text(
                        text = series
                    )
                    val isChecked = remember { mutableStateOf(false) }
                    if (seriesChoose.value.contains(series)) isChecked.value=true
                    Checkbox(checked = isChecked.value, onCheckedChange = {
                        isChecked.value = it
                        if(isChecked.value) seriesChoose.value+=series else seriesChoose.value-=series
                        Log.d("series ", "${seriesChoose.value}")
                    })
                }
            }
        }
    }
    return seriesChoose
}