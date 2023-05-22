package com.example.arsenalmobile.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.arsenalmobile.Models.Blaster

fun getColor(blaster: Blaster): Color {
    when (blaster.series) {
        "MEGA" -> return MegaColor
        "ZOMBIE_STRIKE" -> return ZombieColor
        "ELITE" -> return EliteColor
        "RIVAL" -> return RivalColor
    }
    return Color.Black
}