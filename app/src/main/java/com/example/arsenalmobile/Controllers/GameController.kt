package com.example.arsenalmobile.Controllers

import com.example.arsenalmobile.Models.Game
import retrofit2.http.*

interface GameController {
    @POST("game/new/{id}")
    fun addGame(@Body game: Game, @Path("id") id: Long): Game

    @POST("game/aad/user/{id}")
    fun addUserToGame(@Body game: Game, @Path("id") id: Long): Game

    @GET("game/test")
    fun testos(): String = "sdfghjkl"

    @GET("game/all")
    fun getGames(): List<Game>

    @DELETE("game/del/{id}")
    fun deleteGame(@Path("id") id: Long): String

    @PUT("game/{id}")
    fun updateGame(@Path("id") id: Long, @Body game: Game): Game

    @GET("game/{id}")
    fun findGame(@Path("id") id: Long): Game?

}