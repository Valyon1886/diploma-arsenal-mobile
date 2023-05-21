package com.example.arsenalmobile.Controllers

import com.example.arsenalmobile.Models.Ammo
import retrofit2.http.*

interface AmmoController {
    @POST("ammo/new")
    fun createAmmo(@Body ammo: Ammo): Ammo

    @GET("ammo/find/{id}")
    fun findAmmo(@Path("id") id: Long): Ammo

    @GET("ammo/all")
    fun getAmmo(): List<Ammo>

    @PUT("ammo/{id}")
    fun updateAmmo(@Path("id") id: Long, @Body ammo: Ammo): Ammo

    @DELETE("ammo/remove/{id}")
    fun deleteAmmo(@Path("id") id: Long): String
}