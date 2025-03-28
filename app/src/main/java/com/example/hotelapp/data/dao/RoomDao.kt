package com.example.hotelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hotelapp.data.entity.HotelRoom
import com.example.hotelapp.data.model.RoomStatus
import com.example.hotelapp.data.model.RoomType
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM rooms ORDER BY number ASC")
    fun getAllRooms(): Flow<List<HotelRoom>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM rooms WHERE id = :id")
    fun getRoomById(id: Long): Flow<HotelRoom>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM rooms WHERE status = :status ORDER BY number ASC")
    fun getRoomsByStatus(status: RoomStatus): Flow<List<HotelRoom>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM rooms WHERE type = :type ORDER BY number ASC")
    fun getRoomsByType(type: RoomType): Flow<List<HotelRoom>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM rooms WHERE floor = :floor ORDER BY number ASC")
    fun getRoomsByFloor(floor: Int): Flow<List<HotelRoom>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM rooms WHERE " +
           "status = :status AND " +
           "capacity >= :minCapacity")
    fun getAvailableRoomsWithCapacity(status: RoomStatus = RoomStatus.AVAILABLE, minCapacity: Int): Flow<List<HotelRoom>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(room: HotelRoom): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rooms: List<HotelRoom>)
    
    @Update
    suspend fun update(room: HotelRoom)
    
    @Delete
    suspend fun delete(room: HotelRoom)
    
    @Query("UPDATE rooms SET status = :status WHERE id = :roomId")
    suspend fun updateRoomStatus(roomId: Long, status: RoomStatus)
} 