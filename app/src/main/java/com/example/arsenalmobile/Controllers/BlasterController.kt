package com.example.arsenalmobile.Controllers

import com.example.arsenalmobile.Models.Blaster
import retrofit2.http.*

interface BlasterController {
    @POST("blaster/new")
    fun createBlaster(@Body blaster: Blaster): Blaster

    @GET("blaster/find/{id}")
    fun findBlaster(@Path("id") id: Long): Blaster

    @GET("blaster/all")
    suspend fun getBlasters(): List<Blaster>

    @PUT("blaster/{id}")
    fun updateBlaster(@Path("id") id: Long, @Body blaster: Blaster): Blaster

    @DELETE("blaster/remove/{id}")
    fun deleteBlaster(@Path("id") id: Long): String

}