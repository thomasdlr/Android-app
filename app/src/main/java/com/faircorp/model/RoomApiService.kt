package com.faircorp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomApiService {

    @GET("/api/rooms")//list all the rooms
    fun findAll(): Call<List<RoomDto>>

    @GET("/api/rooms/{id}")//get a specific room
    fun findById(@Path("id") id: Long): Call<RoomDto>

    @GET("/api/rooms/{id}/windows")//get the windows in a room
    fun findWindowsInRoom(@Path("id") id: Long): Call<List<WindowDto>>
}
