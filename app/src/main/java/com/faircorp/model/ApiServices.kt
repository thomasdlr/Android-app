package com.faircorp.model

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class ApiServices {
    val windowsApiService : WindowApiService by lazy {

        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .baseUrl("http://app-bdb4e9a8-155a-47de-9224-1221e0eb0751.cleverapps.io")
            .build()
            .create(WindowApiService::class.java)
    }

    val roomsApiService : RoomApiService by lazy {

        Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .baseUrl("http://app-bdb4e9a8-155a-47de-9224-1221e0eb0751.cleverapps.io")
                .build()
                .create(RoomApiService::class.java)
    }
}