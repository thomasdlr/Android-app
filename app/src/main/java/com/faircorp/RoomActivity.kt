package com.faircorp

import android.os.Bundle
import android.widget.TextView
import com.faircorp.model.RoomService

class RoomActivity : BasicActivity() {


    val roomService = RoomService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val param = intent.getStringExtra(ROOM_NAME_PARAM)
        val roomName = findViewById<TextView>(R.id.txt_room)
        roomName.text = param

        val id = intent.getLongExtra(ROOM_NAME_PARAM, 0)
        val room = roomService.findById(id)

        if (room != null) {

            findViewById<TextView>(R.id.txt_room).text = room.name
            findViewById<TextView>(R.id.txt_room_current_temperature).text = room.currentTemperature?.toString()
            findViewById<TextView>(R.id.txt_room_target_temperature).text = room.targetTemperature?.toString()
            findViewById<TextView>(R.id.txt_room_windows).text = room.windows.toString()


        }
    }
}