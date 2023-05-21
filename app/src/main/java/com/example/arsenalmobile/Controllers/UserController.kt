package com.example.arsenalmobile.Controllers

import com.example.arsenalmobile.Models.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserController {

    @GET("user/get/token/{idToken}")
    suspend fun checkIdTokenUser(@Path("idToken") idToken: String): Boolean

    @GET("user/get/idToken/{idToken}")
    suspend fun getUserByIdToken(@Path("idToken") idToken: String): User

    @POST("user/new")
    suspend fun addUser(@Body user: User): User

    @GET("user/test")
    suspend fun testos(): String = "sdfghjkl"

    @GET("user/all")
    suspend fun getUsers(): List<User>

    @DELETE("user/del/{id}")
    suspend fun deleteUser(@Path("id") id: Long): String

    @PUT("user/{id}")
    suspend fun updateUser(@Path("id") id: Long, @Body user: User): User

    @GET("user/{id}")
    suspend fun findUser(@Path("id") id: Long): User?

}