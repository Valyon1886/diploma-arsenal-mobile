package com.example.arsenalmobile.Entity

data class GameInput(
    var name: String,  // Название игрового сеанса
    var amount: Int,  // Минимальное количество участников
    var image: String,  // Иконка игрового сеанса
    var mode: String,  // Игровой режим (правила игрового сеанса)
    var description: String,
    var destination: String,
    var allowedSeries: List<String>,
    var data: String?,
    var users: List<Long?>,  // Список
    var isActive: Boolean
)