package com.example.arsenalmobile.Navigation

import com.example.arsenalmobile.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String) {
    object Profile: BottomItem("Profile", R.drawable.baseline_person_24, "profile")
    object Games: BottomItem("Games", R.drawable.baseline_videogame_asset_24, "games")
    object Blasters: BottomItem("Blasters", R.drawable.blaster, "blasters")
}