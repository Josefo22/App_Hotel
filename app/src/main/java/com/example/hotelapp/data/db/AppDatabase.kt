package com.example.hotelapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hotelapp.HotelApplication
import com.example.hotelapp.data.dao.*
import com.example.hotelapp.data.model.*

@Database(
    entities = [
        com.example.hotelapp.data.model.Room::class,
        Customer::class,
        Service::class,
        Reservation::class,
        ReservationService::class,
        Invoice::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun roomDao(): RoomDao
    abstract fun customerDao(): CustomerDao
    abstract fun serviceDao(): ServiceDao
    abstract fun reservationDao(): ReservationDao
    abstract fun reservationServiceDao(): ReservationServiceDao
    abstract fun invoiceDao(): InvoiceDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getInstance(context: Context): AppDatabase {
            try {
                return HotelApplication.instance.database
            } catch (e: Exception) {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "hotel_database"
                    )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
} 