package com.example.hotelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hotelapp.data.model.Room
import com.example.hotelapp.data.model.RoomStatus
import com.example.hotelapp.data.model.RoomType

@Dao
interface RoomDao {
    @Query("SELECT * FROM rooms ORDER BY number")
    fun getAllRooms(): LiveData<List<Room>>
    
    @Query("SELECT * FROM rooms WHERE id = :roomId")
    fun getRoomById(roomId: Long): LiveData<Room>
    
    @Query("SELECT * FROM rooms WHERE status = :status ORDER BY number")
    fun getRoomsByStatus(status: RoomStatus): LiveData<List<Room>>
    
    @Query("SELECT * FROM rooms WHERE type = :type ORDER BY number")
    fun getRoomsByType(type: RoomType): LiveData<List<Room>>
    
    @Query("SELECT * FROM rooms WHERE pricePerNight <= :maxPrice ORDER BY pricePerNight")
    fun getRoomsByMaxPrice(maxPrice: Double): LiveData<List<Room>>
    
    @Query("SELECT * FROM rooms WHERE capacity >= :minCapacity ORDER BY capacity")
    fun getRoomsByMinCapacity(minCapacity: Int): LiveData<List<Room>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoom(room: Room): Long
    
    @Update
    suspend fun updateRoom(room: Room)
    
    @Delete
    suspend fun deleteRoom(room: Room)
    
    @Query("UPDATE rooms SET status = :newStatus WHERE id = :roomId")
    suspend fun updateRoomStatus(roomId: Long, newStatus: RoomStatus)
} 