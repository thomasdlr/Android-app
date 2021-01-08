package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//This activity is no longer launched by a click on the "builing windows" menu button, but when a room is selected

class WindowsActivity : BasicActivity(), OnWindowSelectedListener, OnSwitchSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_windows)

        val recyclerView = findViewById<RecyclerView>(R.id.list_windows)
        val adapter = WindowAdapter(this)

        //val param = intent.getStringExtra(ROOM_NAME_PARAM)
        val id = intent.getLongExtra(ROOM_NAME_PARAM, 0)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter


        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().roomsApiService.findWindowsInRoom(id).execute() } //We do not print all the windows but only the window in the chosen room
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            adapter.update(it.body() ?: emptyList())
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


    }



    override fun OnWindowSelected(id: Long) {
        val intent = Intent(this, WindowActivity::class.java).putExtra(WINDOW_NAME_PARAM, id)
        startActivity(intent)
    }

    override fun OnSwitchSelected(id: Long) {

        lifecycleScope.launch(context = Dispatchers.IO) {

            runCatching { ApiServices().windowsApiService.switchWindow(id) }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {}

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

        val intent = Intent(this, WindowActivity::class.java).putExtra(WINDOW_NAME_PARAM, id)
        startActivity(intent)
    }
}