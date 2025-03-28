package com.example.hotelapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelapp.data.entity.HotelRoom
import com.example.hotelapp.data.model.RoomStatus
import com.example.hotelapp.data.model.RoomType
import com.example.hotelapp.databinding.ItemRoomBinding
import java.text.NumberFormat
import java.util.Locale

class RoomAdapter(
    private val onRoomClicked: (HotelRoom) -> Unit,
    private val onReserveClicked: (HotelRoom) -> Unit
) : ListAdapter<HotelRoom, RoomAdapter.RoomViewHolder>(RoomDiffCallback()) {

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

        fun bind(room: HotelRoom) {
            binding.apply {
                tvRoomNumber.text = "Habitación ${room.number}"
                tvRoomType.text = getRoomTypeText(room.type)
                tvRoomStatus.text = getStatusText(room.status)
                tvRoomCapacity.text = "${room.capacity} ${if (room.capacity > 1) "personas" else "persona"}"
                tvRoomPrice.text = getFormattedPrice(room.pricePerNight)

                // Configurar el color del estado
                tvRoomStatus.setTextColor(getStatusColor(room.status))

                // Configurar visibilidad de amenidades
                chipWifi.visibility = if (room.hasWifi) View.VISIBLE else View.GONE
                chipTv.visibility = if (room.hasTV) View.VISIBLE else View.GONE
                chipAc.visibility = if (room.hasAC) View.VISIBLE else View.GONE
                chipMinibar.visibility = if (room.hasMinibar) View.VISIBLE else View.GONE

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

        private fun getFormattedPrice(price: Double): String {
            val format = NumberFormat.getCurrencyInstance(Locale.US)
            return format.format(price)
        }

        private fun getStatusColor(status: RoomStatus): Int {
            return when (status) {
                RoomStatus.AVAILABLE -> android.graphics.Color.GREEN
                RoomStatus.OCCUPIED -> android.graphics.Color.RED
                RoomStatus.MAINTENANCE -> android.graphics.Color.YELLOW
                RoomStatus.RESERVED -> android.graphics.Color.BLUE
                RoomStatus.CLEANING -> android.graphics.Color.GRAY
            }
        }

        private fun getRoomTypeText(type: RoomType): String {
            return when (type) {
                RoomType.SINGLE -> "Individual"
                RoomType.DOUBLE -> "Doble"
                RoomType.TWIN -> "Twin"
                RoomType.SUITE -> "Suite"
                RoomType.DELUXE -> "Deluxe"
                RoomType.STANDARD -> "Estándar"
                RoomType.LUXURY -> "Lujo"
                RoomType.FAMILY -> "Familiar"
            }
        }

        private fun getStatusText(status: RoomStatus): String {
            return when (status) {
                RoomStatus.AVAILABLE -> "Disponible"
                RoomStatus.OCCUPIED -> "Ocupada"
                RoomStatus.MAINTENANCE -> "Mantenimiento"
                RoomStatus.RESERVED -> "Reservada"
                RoomStatus.CLEANING -> "Limpieza"
            }
        }
    }
}

class RoomDiffCallback : DiffUtil.ItemCallback<HotelRoom>() {
    override fun areItemsTheSame(oldItem: HotelRoom, newItem: HotelRoom): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HotelRoom, newItem: HotelRoom): Boolean {
        return oldItem == newItem
    }
} 