package com.example.hotelapp

import android.app.Application
import androidx.room.Room
import com.example.hotelapp.data.db.AppDatabase

class HotelApplication : Application() {
    
    companion object {
        lateinit var instance: HotelApplication
            private set
    }
    
    lateinit var database: AppDatabase
        private set
    
    override fun onCreate() {
        super.onCreate()
        instance = this
        
        // Inicializar la base de datos
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "hotel_database"
        )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries() // Solo para desarrollo
        .build()
    }
} 