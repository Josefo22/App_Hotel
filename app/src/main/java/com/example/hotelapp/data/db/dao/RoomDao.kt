package com.example.hotelapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hotelapp.data.model.Room

@Dao
interface RoomDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(room: Room): Long
    
    @Update
    suspend fun update(room: Room)
    
    @Delete
    suspend fun delete(room: Room)
    
    @Query("SELECT * FROM rooms")
    fun getAllRooms(): LiveData<List<Room>>
    
    @Query("SELECT * FROM rooms WHERE id = :roomId")
    fun getRoomById(roomId: Long): LiveData<Room>
    
    @Query("SELECT * FROM rooms WHERE status = :status")
    fun getRoomsByStatus(status: String): LiveData<List<Room>>
    
    @Query("SELECT * FROM rooms WHERE type = :type")
    fun getRoomsByType(type: String): LiveData<List<Room>>
    
    @Query("SELECT * FROM rooms WHERE capacity >= :minCapacity")
    fun getRoomsByCapacity(minCapacity: Int): LiveData<List<Room>>
    
    @Query("DELETE FROM rooms")
    suspend fun deleteAll()
} 