package com.faircorp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.R
import com.faircorp.WindowsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.AccessController.getContext

class WindowAdapter(val listener: WindowsActivity): RecyclerView.Adapter<WindowAdapter.WindowViewHolder>() {

    inner class WindowViewHolder(view: View) : RecyclerView.ViewHolder(view) { // (2)
        val name: TextView = view.findViewById(R.id.txt_window_name)
        val room: TextView = view.findViewById(R.id.txt_window_room)
        val status: TextView = view.findViewById(R.id.txt_status)
    }

    private val items = mutableListOf<WindowDto>() // (3)



    fun update(windows: List<WindowDto>) {  // (4)
        items.clear()
        items.addAll(windows)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size // (5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindowViewHolder { // (6)
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_windows_item, parent, false)
        return WindowViewHolder(view)
    }


    override fun onBindViewHolder(holder: WindowViewHolder, position: Int) {
        var window = items[position]

        holder.apply {
            name.text = window.name
            status.text = window.windowStatus.toString()
            room.text = window.room.name

            itemView.setOnClickListener { listener.OnWindowSelected(window.id) }



        }
    }

    override fun onViewRecycled(holder: WindowViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}
