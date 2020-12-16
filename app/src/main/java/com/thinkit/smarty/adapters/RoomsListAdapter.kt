package com.thinkit.smarty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thinkit.smarty.databinding.ItemRoomBinding
import com.thinkit.smarty.entities.RoomWithDevices


class RoomsListAdapter(var roomsWithDevices: List<RoomWithDevices>) : RecyclerView.Adapter<RoomsListAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemRoomBinding =
            ItemRoomBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val roomWithDevices = roomsWithDevices[position]
        holder.viewBinding.roomWithDevices=roomWithDevices

    }

    override fun getItemCount(): Int = roomsWithDevices.size
    inner class ViewHolder(val viewBinding: ItemRoomBinding) : RecyclerView.ViewHolder(
        viewBinding.root
    )




}