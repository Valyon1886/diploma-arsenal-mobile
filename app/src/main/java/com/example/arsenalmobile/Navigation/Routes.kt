package com.example.arsenalmobile.Navigation

sealed class Routes(val route: String) {
    object CreateGame: Routes("create_game")
    object UserTest: Routes("user_test")
    object UserBlasters: Routes("user_blasters")
    object UserGames: Routes("user_games")
    object Loading: Routes("loading")
    object InfoBlaster: Routes("info_blaster")
    object InfoGame: Routes("info_game")
    object UserRegistration: Routes("user_registration")
}