package com.dasyam.vmdirectoryapp.ofc_rooms.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dasyam.vmdirectoryapp.R
import com.dasyam.vmdirectoryapp.databinding.ItemOfcRoomBinding
import com.dasyam.vmdirectoryapp.ofc_rooms.OfcRoom

class OfcRoomListAdapter : RecyclerView.Adapter<OfcRoomListAdapter.OfcRoomViewHolder>() {

    private val roomList = mutableListOf<OfcRoom>()

    class OfcRoomViewHolder(val itemBinding: ItemOfcRoomBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfcRoomViewHolder {
        val itemBinding = ItemOfcRoomBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return OfcRoomViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: OfcRoomViewHolder, position: Int) {
        val room = roomList[position]
        holder.itemBinding.tvRoomName.text = room.name
        holder.itemBinding.tvMaxOccupancy.text =
            holder.itemView.context.resources.getString(
                R.string.max_occupancy, room.maxOccupancy
            )
        holder.itemBinding.swOccupancy.isChecked = room.isOccupied
        val isAvailable = if (room.isOccupied) "Available" else "Unavailable"
        holder.itemBinding.root.contentDescription = "Office room ${position}: " +
                "${room.name}, maximum occupancy ${room.maxOccupancy} is  $isAvailable"
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    fun bindRooms(rmList: List<OfcRoom>) {
        roomList.clear()
        roomList.addAll(rmList)
        notifyDataSetChanged()
    }

    fun getRoomList(): List<OfcRoom> {
        return roomList
    }
}