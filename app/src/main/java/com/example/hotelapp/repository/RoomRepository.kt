package com.example.hotelapp.repository

import com.example.hotelapp.data.dao.RoomDao
import com.example.hotelapp.data.entity.HotelRoom
import com.example.hotelapp.data.model.RoomStatus
import com.example.hotelapp.data.model.RoomType
import kotlinx.coroutines.flow.Flow

class RoomRepository(private val roomDao: RoomDao) {
    val allRooms: Flow<List<HotelRoom>> = roomDao.getAllRooms()
    
    fun getRoomById(id: Long): Flow<HotelRoom> {
        return roomDao.getRoomById(id)
    }
    
    fun getRoomsByStatus(status: RoomStatus): Flow<List<HotelRoom>> {
        return roomDao.getRoomsByStatus(status)
    }
    
    fun getRoomsByType(type: RoomType): Flow<List<HotelRoom>> {
        return roomDao.getRoomsByType(type)
    }
    
    fun getRoomsByFloor(floor: Int): Flow<List<HotelRoom>> {
        return roomDao.getRoomsByFloor(floor)
    }
    
    fun getAvailableRoomsWithCapacity(minCapacity: Int): Flow<List<HotelRoom>> {
        return roomDao.getAvailableRoomsWithCapacity(RoomStatus.AVAILABLE, minCapacity)
    }
    
    suspend fun insert(room: HotelRoom): Long {
        return roomDao.insert(room)
    }
    
    suspend fun update(room: HotelRoom) {
        roomDao.update(room)
    }
    
    suspend fun delete(room: HotelRoom) {
        roomDao.delete(room)
    }
    
    suspend fun updateRoomStatus(roomId: Long, status: RoomStatus) {
        roomDao.updateRoomStatus(roomId, status)
    }
} 