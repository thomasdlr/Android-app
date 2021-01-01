package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText


const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"
const val ROOM_NAME_PARAM = "com.faircorp.roomname.attribute"


class MainActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Called when the user taps the button */
    fun openWindow(view: View) {
        val windowName = findViewById<EditText>(R.id.txt_window_name).text.toString()

        // Do something in response to button
        val intent = Intent(this, WindowActivity::class.java).apply {
            putExtra(WINDOW_NAME_PARAM, windowName)
        }
        startActivity(intent)
    }


    fun openRoom(view: View) {
        // Extract value filled in editext identified with txt_room id
        val roomName = findViewById<EditText>(R.id.txt_room).text.toString()

        // Do something in response to button
        val intent = Intent(this, RoomActivity::class.java).apply {
            putExtra(ROOM_NAME_PARAM, roomName)
        }

        startActivity(intent)
    }


}



