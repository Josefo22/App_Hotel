package com.example.hotelapp

import android.app.Application
import com.example.hotelapp.data.AppDatabase
import com.example.hotelapp.repository.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HotelApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    
    val roomRepository by lazy { RoomRepository(database.roomDao()) }
    val customerRepository by lazy { CustomerRepository(database.customerDao()) }
    val serviceRepository by lazy { ServiceRepository(database.serviceDao()) }
    val reservationRepository by lazy { ReservationRepository(database.reservationDao()) }
    val reservationServiceRepository by lazy { ReservationServiceRepository(database.reservationServiceDao()) }
    val invoiceRepository by lazy { InvoiceRepository(database.invoiceDao()) }
} 