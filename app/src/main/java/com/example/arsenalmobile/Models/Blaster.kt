package com.example.arsenalmobile.Models

data class Blaster(
    var blasterName: String,  // Название бластера
    var ammo: MutableList<Ammo>?, // Список используемых типов боеприпасов                       // map колличество патронов в сумме (в описании говорится сколько конкретно чего)
    var amount: Int,  //  Количество боеприпасов
    var image: String,  //  Изображение бластера (отображается в каталоге)
    var series: String,  //  Название серии к которой принадлежит бластер
    var category: String,//Category,
    var author: String,  //  Ник пользователя, добавившего бластер (поумолчанию админ)
    var id: Long?
)