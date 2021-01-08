package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.model.ApiServices
import com.faircorp.model.WindowAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WindowActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val param = intent.getStringExtra(WINDOW_NAME_PARAM)
        val windowName = findViewById<TextView>(R.id.txt_window_name)
        windowName.text = param

        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.findById(id).execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            findViewById<TextView>(R.id.txt_window_name).text = it.body()?.name ?: "undefined"
                            findViewById<TextView>(R.id.txt_room_name).text = it.body()?.room?.name ?: "undefined"
                            findViewById<TextView>(R.id.txt_window_current_temperature).text = it.body()?.room?.currentTemperature?.toString()
                            findViewById<TextView>(R.id.txt_window_target_temperature).text =  it.body()?.room?.targetTemperature?.toString()
                            findViewById<TextView>(R.id.txt_window_status).text = it.body()?.windowStatus?.toString()
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) {
                            Toast.makeText(
                                    applicationContext,
                                    "Error on windows loading $it",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }
        }



        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            lifecycleScope.launch(context = Dispatchers.IO) {

                runCatching { ApiServices().windowsApiService.switchWindow(id).execute() }
                        .onSuccess {
                            withContext(context = Dispatchers.Main) {
                                findViewById<TextView>(R.id.txt_window_status).text = it.body()?.windowStatus?.toString()


                            }

                        }
                        .onFailure {
                            withContext(context = Dispatchers.Main) {
                                Toast.makeText(
                                        applicationContext,
                                        "Error on switching window status",
                                        Toast.LENGTH_LONG
                                ).show()
                            }
                        }
            }


        }
    }
}
