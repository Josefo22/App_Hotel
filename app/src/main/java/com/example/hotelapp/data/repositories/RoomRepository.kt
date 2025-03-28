package com.example.hotelapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.hotelapp.data.dao.RoomDao
import com.example.hotelapp.data.entity.HotelRoom
import com.example.hotelapp.data.model.RoomStatus
import com.example.hotelapp.data.model.RoomType
import kotlinx.coroutines.flow.Flow

class RoomRepository(private val roomDao: RoomDao) {
    
    fun getAllRooms(): LiveData<List<HotelRoom>> {
        return roomDao.getAllRooms().asLiveData()
    }
    
    fun getRoomById(id: Long): LiveData<HotelRoom> {
        return roomDao.getRoomById(id).asLiveData()
    }
    
    fun getRoomsByStatus(status: RoomStatus): LiveData<List<HotelRoom>> {
        return roomDao.getRoomsByStatus(status).asLiveData()
    }
    
    fun getRoomsByType(type: RoomType): LiveData<List<HotelRoom>> {
        return roomDao.getRoomsByType(type).asLiveData()
    }
    
    fun getRoomsByFloor(floor: Int): LiveData<List<HotelRoom>> {
        return roomDao.getRoomsByFloor(floor).asLiveData()
    }
    
    fun getAvailableRoomsWithCapacity(minCapacity: Int): LiveData<List<HotelRoom>> {
        return roomDao.getAvailableRoomsWithCapacity(RoomStatus.AVAILABLE, minCapacity).asLiveData()
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