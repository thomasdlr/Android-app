package com.faircorp

import android.os.Bundle
import android.widget.TextView
import com.faircorp.model.WindowService

//const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"


class WindowActivity : BasicActivity() {

    val windowService = WindowService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val param = intent.getStringExtra(WINDOW_NAME_PARAM)
        val windowName = findViewById<TextView>(R.id.txt_window_name)
        windowName.text = param

        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
        val window = windowService.findById(id)

        if (window != null) {

            findViewById<TextView>(R.id.txt_window_name).text = window.name
            findViewById<TextView>(R.id.txt_room_name).text = window.room.name
            findViewById<TextView>(R.id.txt_window_current_temperature).text = window.room.currentTemperature?.toString()
            findViewById<TextView>(R.id.txt_window_target_temperature).text = window.room.targetTemperature?.toString()
            findViewById<TextView>(R.id.txt_window_status).text = window.status.toString()



            //windowName.text = param
        }
    }
}
