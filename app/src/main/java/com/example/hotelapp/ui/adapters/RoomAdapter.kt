package com.example.hotelapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelapp.data.model.Room
import com.example.hotelapp.data.model.RoomStatus
import com.example.hotelapp.data.model.RoomType
import com.example.hotelapp.databinding.ItemRoomBinding

class RoomAdapter(
    private val onRoomClicked: (Room) -> Unit,
    private val onReserveClicked: (Room) -> Unit
) : ListAdapter<Room, RoomAdapter.RoomViewHolder>(RoomDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RoomViewHolder(private val binding: ItemRoomBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(room: Room) {
            binding.apply {
                tvRoomNumber.text = "Habitación ${room.number}"
                tvRoomType.text = getRoomTypeText(room.type)
                tvRoomStatus.text = getStatusText(room.status)
                tvRoomCapacity.text = "${room.capacity} ${if (room.capacity > 1) "personas" else "persona"}"
                tvRoomPrice.text = room.getFormattedPrice()
                
                // Configurar el color del estado
                tvRoomStatus.setTextColor(room.getStatusColor())
                
                // Configurar visibilidad de amenidades
                val hasWifi = room.amenities.any { it.contains("WiFi", ignoreCase = true) }
                val hasTv = room.amenities.any { it.contains("TV", ignoreCase = true) }
                val hasAc = room.amenities.any { it.contains("Aire", ignoreCase = true) }
                val hasMinibar = room.amenities.any { it.contains("Minibar", ignoreCase = true) }
                
                chipWifi.visibility = if (hasWifi) View.VISIBLE else View.GONE
                chipTv.visibility = if (hasTv) View.VISIBLE else View.GONE
                chipAc.visibility = if (hasAc) View.VISIBLE else View.GONE
                chipMinibar.visibility = if (hasMinibar) View.VISIBLE else View.GONE
                
                // Configurar botones
                btnViewRoom.setOnClickListener { onRoomClicked(room) }
                
                // El botón de reserva solo está habilitado si la habitación está disponible
                btnReserveRoom.isEnabled = room.status == RoomStatus.AVAILABLE
                btnReserveRoom.setOnClickListener { 
                    if (room.status == RoomStatus.AVAILABLE) {
                        onReserveClicked(room)
                    }
                }
            }
        }
        
        private fun getRoomTypeText(type: RoomType): String {
            return when (type) {
                RoomType.SINGLE -> "Individual"
                RoomType.DOUBLE -> "Doble"
                RoomType.SUITE -> "Suite"
                RoomType.LUXURY -> "Lujo"
                RoomType.FAMILY -> "Familiar"
            }
        }
        
        private fun getStatusText(status: RoomStatus): String {
            return when (status) {
                RoomStatus.AVAILABLE -> "Disponible"
                RoomStatus.OCCUPIED -> "Ocupada"
                RoomStatus.MAINTENANCE -> "Mantenimiento"
            }
        }
    }
}

class RoomDiffCallback : DiffUtil.ItemCallback<Room>() {
    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem == newItem
    }
} 