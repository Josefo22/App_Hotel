package com.example.hotelapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hotelapp.data.db.AppDatabase
import com.example.hotelapp.data.model.Room
import com.example.hotelapp.data.model.RoomStatus
import com.example.hotelapp.data.model.RoomType
import com.example.hotelapp.repository.RoomRepository
import com.example.hotelapp.utils.SampleDataProvider
import kotlinx.coroutines.launch

class RoomViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: RoomRepository
    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: LiveData<List<Room>> = _rooms
    
    init {
        val roomDao = AppDatabase.getInstance(application).roomDao()
        repository = RoomRepository(roomDao)
        loadAllRooms()
    }
    
    fun loadAllRooms() {
        viewModelScope.launch {
            val roomsFromDb = repository.getAllRooms().value
            if (roomsFromDb.isNullOrEmpty()) {
                // Si no hay habitaciones en la base de datos, cargamos datos de ejemplo
                _rooms.value = SampleDataProvider.getSampleRooms()
            } else {
                _rooms.value = roomsFromDb
            }
        }
    }
    
    fun loadRoomsByStatus(status: RoomStatus) {
        viewModelScope.launch {
            val filteredRooms = rooms.value?.filter { it.status == status } ?: emptyList()
            _rooms.value = filteredRooms
        }
    }
    
    fun loadRoomsByType(type: RoomType) {
        viewModelScope.launch {
            val filteredRooms = rooms.value?.filter { it.type == type } ?: emptyList()
            _rooms.value = filteredRooms
        }
    }
    
    fun getRoomById(roomId: Long): Room? {
        return rooms.value?.find { it.id == roomId }
    }
    
    fun insertRoom(room: Room) {
        viewModelScope.launch {
            repository.insertRoom(room)
            loadAllRooms()
        }
    }
    
    fun updateRoom(room: Room) {
        viewModelScope.launch {
            repository.updateRoom(room)
            loadAllRooms()
        }
    }
    
    fun deleteRoom(room: Room) {
        viewModelScope.launch {
            repository.deleteRoom(room)
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