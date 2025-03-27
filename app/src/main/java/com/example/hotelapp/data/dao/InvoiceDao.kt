package com.example.hotelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hotelapp.data.model.Invoice
import com.example.hotelapp.data.model.PaymentMethod
import com.example.hotelapp.data.model.PaymentStatus
import java.util.Date

@Dao
interface InvoiceDao {
    @Query("SELECT * FROM invoices ORDER BY issueDate DESC")
    fun getAllInvoices(): LiveData<List<Invoice>>
    
    @Query("SELECT * FROM invoices WHERE id = :invoiceId")
    fun getInvoiceById(invoiceId: Long): LiveData<Invoice>
    
    @Query("SELECT * FROM invoices WHERE reservationId = :reservationId")
    fun getInvoiceByReservation(reservationId: Long): LiveData<Invoice>
    
    @Query("SELECT * FROM invoices WHERE customerId = :customerId ORDER BY issueDate DESC")
    fun getInvoicesByCustomer(customerId: Long): LiveData<List<Invoice>>
    
    @Query("SELECT * FROM invoices WHERE paymentStatus = :status ORDER BY issueDate DESC")
    fun getInvoicesByPaymentStatus(status: PaymentStatus): LiveData<List<Invoice>>
    
    @Query("SELECT * FROM invoices WHERE paymentMethod = :method ORDER BY issueDate DESC")
    fun getInvoicesByPaymentMethod(method: PaymentMethod): LiveData<List<Invoice>>
    
    @Query("SELECT * FROM invoices WHERE dueDate < :today AND paymentStatus != :paidStatus ORDER BY dueDate")
    fun getOverdueInvoices(today: Date, paidStatus: PaymentStatus = PaymentStatus.PAID): LiveData<List<Invoice>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvoice(invoice: Invoice): Long
    
    @Update
    suspend fun updateInvoice(invoice: Invoice)
    
    @Delete
    suspend fun deleteInvoice(invoice: Invoice)
    
    @Query("UPDATE invoices SET paymentStatus = :status, paymentMethod = :method, paymentDate = :date WHERE id = :invoiceId")
    suspend fun updateInvoicePayment(invoiceId: Long, status: PaymentStatus, method: PaymentMethod, date: Date)
} 