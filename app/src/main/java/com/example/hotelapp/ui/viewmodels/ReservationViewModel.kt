package com.example.hotelapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hotelapp.data.AppDatabase
import com.example.hotelapp.data.entity.Reservation
import com.example.hotelapp.data.model.ReservationStatus
import com.example.hotelapp.data.repositories.ReservationRepository
import kotlinx.coroutines.launch
import java.util.Date

class ReservationViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: ReservationRepository
    val allReservations: LiveData<List<Reservation>>
    
    init {
        val reservationDao = AppDatabase.getDatabase(application, viewModelScope).reservationDao()
        repository = ReservationRepository(reservationDao)
        allReservations = repository.getAllReservations()
    }
    
    fun getReservationById(id: Long): LiveData<Reservation> {
        return repository.getReservationById(id)
    }
    
    fun getReservationsByCustomer(customerId: Long): LiveData<List<Reservation>> {
        return repository.getReservationsByCustomer(customerId)
    }
    
    fun getReservationsByRoom(roomId: Long): LiveData<List<Reservation>> {
        return repository.getReservationsByRoom(roomId)
    }
    
    fun getReservationsByStatus(status: ReservationStatus): LiveData<List<Reservation>> {
        return repository.getReservationsByStatus(status)
    }
    
    fun getReservationsInDateRange(startDate: Date, endDate: Date): LiveData<List<Reservation>> {
        return repository.getReservationsInDateRange(startDate, endDate)
    }
    
    suspend fun getConflictingReservations(
        roomId: Long,
        checkInDate: Date,
        checkOutDate: Date,
        excludeStatus: ReservationStatus = ReservationStatus.CANCELLED
    ): List<Reservation> {
        return repository.getConflictingReservations(roomId, checkInDate, checkOutDate, excludeStatus)
    }
    
    fun insert(reservation: Reservation) = viewModelScope.launch {
        repository.insert(reservation)
    }
    
    fun update(reservation: Reservation) = viewModelScope.launch {
        repository.update(reservation)
    }
    
    fun delete(reservation: Reservation) = viewModelScope.launch {
        repository.delete(reservation)
    }
    
    fun updateReservationStatus(reservationId: Long, status: ReservationStatus) = viewModelScope.launch {
        repository.updateReservationStatus(reservationId, status)
    }
} 