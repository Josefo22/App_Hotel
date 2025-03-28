package com.example.hotelapp.data.dao

import androidx.room.*
import com.example.hotelapp.data.entity.Invoice
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface InvoiceDao {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM invoices ORDER BY issueDate DESC")
    fun getAllInvoices(): Flow<List<Invoice>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM invoices WHERE id = :id")
    fun getInvoiceById(id: Long): Flow<Invoice>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM invoices WHERE reservationId = :reservationId")
    fun getInvoiceByReservation(reservationId: Long): Flow<Invoice?>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM invoices WHERE paymentStatus = :status ORDER BY issueDate DESC")
    fun getInvoicesByPaymentStatus(status: String): Flow<List<Invoice>>
    
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM invoices WHERE issueDate BETWEEN :startDate AND :endDate ORDER BY issueDate DESC")
    fun getInvoicesByDateRange(startDate: Date, endDate: Date): Flow<List<Invoice>>
    
    @Query("SELECT SUM(total) FROM invoices WHERE paymentStatus = :status")
    fun getTotalRevenue(status: String = "PAID"): Flow<Double?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(invoice: Invoice): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(invoices: List<Invoice>)
    
    @Update
    suspend fun update(invoice: Invoice)
    
    @Delete
    suspend fun delete(invoice: Invoice)
    
    @Query("UPDATE invoices SET paymentStatus = :status, paymentDate = :paymentDate, paymentMethod = :paymentMethod WHERE id = :invoiceId")
    suspend fun updatePaymentStatus(invoiceId: Long, status: String, paymentDate: Date?, paymentMethod: String?)
} 