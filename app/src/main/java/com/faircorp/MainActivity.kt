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
}



