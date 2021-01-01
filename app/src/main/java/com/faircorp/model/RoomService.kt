package com.faircorp.model

class RoomService {

    companion object {


        // Fake rooms
        val ROOMS: List<RoomDto> = listOf(
                RoomDto(1, "Room EF 6.10", 18.2, 20.0, listOf("Entry Window","Back Window") ),
                RoomDto(2, "Hall", 18.2, 18.0, listOf("Sliding door")),
                RoomDto(3, "Room EF 7.10", 21.2, 20.0, listOf("Window 1","Window 2"))
        )



    }

    fun findById(id: Long) = ROOMS.firstOrNull { it.id == id }

    fun findAll() = ROOMS.sortedBy { it.name }
}
