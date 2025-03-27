package com.example.hotelapp.data.repositories

import androidx.lifecycle.LiveData
import com.example.hotelapp.data.db.dao.RoomDao
import com.example.hotelapp.data.model.Room

class RoomRepository(private val roomDao: RoomDao) {
    
    fun getAllRooms(): LiveData<List<Room>> {
        return roomDao.getAllRooms()
    }
    
    fun getRoomById(id: Long): LiveData<Room> {
        return roomDao.getRoomById(id)
    }
    
    fun getRoomsByStatus(status: String): LiveData<List<Room>> {
        return roomDao.getRoomsByStatus(status)
    }
    
    fun getRoomsByType(type: String): LiveData<List<Room>> {
        return roomDao.getRoomsByType(type)
    }
    
    fun getRoomsByCapacity(minCapacity: Int): LiveData<List<Room>> {
        return roomDao.getRoomsByCapacity(minCapacity)
    }
    
    suspend fun insert(room: Room): Long {
        return roomDao.insert(room)
    }
    
    suspend fun update(room: Room) {
        roomDao.update(room)
    }
    
    suspend fun delete(room: Room) {
        roomDao.delete(room)
    }
    
    suspend fun deleteAll() {
        roomDao.deleteAll()
    }
} 