package com.example.arsenalmobile.Entity

import com.example.arsenalmobile.Models.Blaster
import com.example.arsenalmobile.Models.Game

class UserInput (
    var idToken: String?,
    var userName: String,  // Никнейм пользователя
//    val password: String,  // Пароль пользователя (хранится в шифрованном виде)
    var image: String?,  // Аватар пользователя
    var role: String,  // Роль пользователя (user или admin)
    var arsenal: MutableList<Blaster>?,  // Список оборудования, добавленных пользователем в арсенал
    var games: MutableList<Game>?,  // Список игровых сессий, в которых учавствовал пользователь
)