package com.example.hotelapp.repository

import androidx.lifecycle.LiveData
import com.example.hotelapp.data.dao.RoomDao
import com.example.hotelapp.data.model.Room
import com.example.hotelapp.data.model.RoomStatus
import com.example.hotelapp.data.model.RoomType

class RoomRepository(private val roomDao: RoomDao) {
    
    fun getAllRooms(): LiveData<List<Room>> {
        return roomDao.getAllRooms()
    }
    
    fun getRoomById(roomId: Long): LiveData<Room> {
        return roomDao.getRoomById(roomId)
    }
    
    fun getRoomsByStatus(status: RoomStatus): LiveData<List<Room>> {
        return roomDao.getRoomsByStatus(status)
    }
    
    fun getRoomsByType(type: RoomType): LiveData<List<Room>> {
        return roomDao.getRoomsByType(type)
    }
    
    fun getRoomsByMaxPrice(maxPrice: Double): LiveData<List<Room>> {
        return roomDao.getRoomsByMaxPrice(maxPrice)
    }
    
    fun getRoomsByMinCapacity(minCapacity: Int): LiveData<List<Room>> {
        return roomDao.getRoomsByMinCapacity(minCapacity)
    }
    
    suspend fun insertRoom(room: Room): Long {
        return roomDao.insertRoom(room)
    }
    
    suspend fun updateRoom(room: Room) {
        roomDao.updateRoom(room)
    }
    
    suspend fun deleteRoom(room: Room) {
        roomDao.deleteRoom(room)
    }
    
    suspend fun updateRoomStatus(roomId: Long, newStatus: RoomStatus) {
        roomDao.updateRoomStatus(roomId, newStatus)
    }
} 