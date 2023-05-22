package com.example.arsenalmobile.Models

data class Game (
    var name: String,  // Название игрового сеанса
    var amount: Int,  // Минимальное количество участников
    var image: String,  // Иконка игрового сеанса
    var mode: String,  // Игровой режим (правила игрового сеанса)
    var description: String,
    var destination: String,
    var users: MutableList<Long>?,  // Список
    var isActive: Boolean,
    var id: Long
)