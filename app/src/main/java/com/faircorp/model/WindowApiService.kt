package com.faircorp.model

import retrofit2.Call
import retrofit2.http.*

interface WindowApiService {

    @GET("/api/windows") //list all windows
    fun findAll(): Call<List<WindowDto>>

    @GET("/api/windows/{id}") //get a specific window
    fun findById(@Path("id") id: Long): Call<WindowDto>

    @PUT("/api/windows/{id}/switch") //swith the status of a window
    fun switchWindow(@Path("id") id: Long): Call<WindowDto>

}