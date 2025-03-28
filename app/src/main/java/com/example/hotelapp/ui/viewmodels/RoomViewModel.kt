package com.example.hotelapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hotelapp.data.AppDatabase
import com.example.hotelapp.data.entity.HotelRoom
import com.example.hotelapp.data.model.RoomStatus
import com.example.hotelapp.data.model.RoomType
import com.example.hotelapp.data.repositories.RoomRepository
import com.example.hotelapp.utils.SampleDataProvider
import kotlinx.coroutines.launch

class RoomViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: RoomRepository
    private val _rooms = MutableLiveData<List<HotelRoom>>()
    val rooms: LiveData<List<HotelRoom>> = _rooms
    
    init {
        val roomDao = AppDatabase.getDatabase(application, viewModelScope).roomDao()
        repository = RoomRepository(roomDao)
        loadAllRooms()
    }
    
    fun loadAllRooms() {
        viewModelScope.launch {
            val roomsFromDb = repository.getAllRooms().value
            if (roomsFromDb.isNullOrEmpty()) {
                // Si no hay habitaciones en la base de datos, cargamos datos de ejemplo
                _rooms.value = SampleDataProvider().getSampleRooms()
            } else {
                _rooms.value = roomsFromDb
            }
        }
    }
    
    fun loadRoomsByStatus(status: RoomStatus) {
        viewModelScope.launch {
            val roomsFromDb = repository.getRoomsByStatus(status).value
            _rooms.value = roomsFromDb ?: emptyList()
        }
    }
    
    fun loadRoomsByType(type: RoomType) {
        viewModelScope.launch {
            val roomsFromDb = repository.getRoomsByType(type).value
            _rooms.value = roomsFromDb ?: emptyList()
        }
    }
    
    fun getRoomById(roomId: Long): HotelRoom? {
        return rooms.value?.find { it.id == roomId }
    }
    
    fun insertRoom(room: HotelRoom) {
        viewModelScope.launch {
            repository.insert(room)
            loadAllRooms()
        }
    }
    
    fun updateRoom(room: HotelRoom) {
        viewModelScope.launch {
            repository.update(room)
            loadAllRooms()
        }
    }
    
    fun deleteRoom(room: HotelRoom) {
        viewModelScope.launch {
            repository.delete(room)
            loadAllRooms()
        }
    }
    
    fun updateRoomStatus(roomId: Long, newStatus: RoomStatus) {
        viewModelScope.launch {
            repository.updateRoomStatus(roomId, newStatus)
            loadAllRooms()
        }
    }
} 