package com.faircorp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.R
import com.faircorp.RoomsActivity

class RoomAdapter(val listener: RoomsActivity): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) { // (2)
        val name: TextView = view.findViewById(R.id.txt_room)
        val current_temp: TextView = view.findViewById(R.id.txt_room_current_temp)
        val target_temp: TextView = view.findViewById(R.id.txt_room_target_temp)
        val floor: TextView = view.findViewById(R.id.txt_floor)
        val building: TextView = view.findViewById(R.id.txt_building)

    }

    private val items = mutableListOf<RoomDto>() // (3)

    fun update(rooms: List<RoomDto>) {  // (4)
        items.clear()
        items.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size // (5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder { // (6)
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_rooms_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = items[position]
        holder.apply {
            name.text = room.name

            //We print the value of the characteristics of the room

            val st1 = room.currentTemperature?.toString()
            val st2 = room.targetTemperature?.toString()
            val st3 = room.floor?.toString()
            val st4 = room.building?.name

            current_temp.text = "Current temperature : $st1"
            target_temp.text = "Target temperature : $st2"
            floor.text = "Floor : $st3"
            building.text = "Building : $st4"

            itemView.setOnClickListener { listener.OnRoomSelected(room.id) } // (1)
        }
    }

    override fun onViewRecycled(holder: RoomViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}
