package com.example.petstoretest.api

import com.example.petstoretest.model.Pet
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PetStoreService {
    @GET("pet/{petId}")
    fun getPetById(@Path("petId") petId: Long): Call<Pet>

    @POST("pet")
    fun addPet(@Body pet: Pet): Call<Pet>

    @GET("pet/findByStatus")
    fun getPets(): Call<List<Pet>>

    @GET("pet/findByStatus")
    fun getPetsByStatus(@Query("status") status: String): Call<List<Pet>>
}
