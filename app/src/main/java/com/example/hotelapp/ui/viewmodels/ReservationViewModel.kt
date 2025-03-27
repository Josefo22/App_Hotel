package com.example.hotelapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hotelapp.data.db.AppDatabase
import com.example.hotelapp.data.model.Reservation
import com.example.hotelapp.data.model.ReservationStatus
import com.example.hotelapp.repository.ReservationRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date

class ReservationViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: ReservationRepository
    
    init {
        val reservationDao = AppDatabase.getInstance(application).reservationDao()
        repository = ReservationRepository(reservationDao)
    }
    
    val allReservations: LiveData<List<Reservation>> = repository.allReservations
    
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
    
    fun getReservationsByDateRange(startDate: Date, endDate: Date): LiveData<List<Reservation>> {
        return repository.getReservationsByDateRange(startDate, endDate)
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