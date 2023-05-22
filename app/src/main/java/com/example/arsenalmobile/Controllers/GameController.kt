package com.example.arsenalmobile.Controllers

import com.example.arsenalmobile.Models.Game
import retrofit2.http.*

interface GameController {
    @POST("game/new/{id}")
    fun addGame(@Body game: Game, @Path("id") id: Long): Game

    @PUT("game/add/user/{id}")
    suspend fun addUserToGame(@Body game: Game, @Path("id") id: Long): Game

    @GET("game/users/{id}")
    suspend fun getUserNames(@Path("id") id: Long): MutableList<String>

    @PUT("game/active")
    suspend fun endActive(@Body game: Game): Game

    @GET("game/test")
    fun testos(): String = "sdfghjkl"

    @GET("game/all")
    suspend fun getGames(): List<Game>

    @DELETE("game/del/{id}")
    fun deleteGame(@Path("id") id: Long): String

    @PUT("game/{id}")
    fun updateGame(@Path("id") id: Long, @Body game: Game): Game

    @GET("game/{id}")
    suspend fun findGame(@Path("id") id: Long): Game

}