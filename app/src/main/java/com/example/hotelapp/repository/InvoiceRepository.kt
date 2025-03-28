package com.example.hotelapp.repository

import androidx.lifecycle.LiveData
import com.example.hotelapp.data.dao.InvoiceDao
import com.example.hotelapp.data.entity.Invoice
import kotlinx.coroutines.flow.Flow
import java.util.Date

class InvoiceRepository(private val invoiceDao: InvoiceDao) {
    
    val allInvoices: Flow<List<Invoice>> = invoiceDao.getAllInvoices()
    
    suspend fun insert(invoice: Invoice): Long {
        return invoiceDao.insert(invoice)
    }
    
    suspend fun update(invoice: Invoice) {
        invoiceDao.update(invoice)
    }
    
    suspend fun delete(invoice: Invoice) {
        invoiceDao.delete(invoice)
    }
    
    fun getInvoiceById(id: Long): Flow<Invoice> {
        return invoiceDao.getInvoiceById(id)
    }
    
    fun getInvoiceByReservation(reservationId: Long): Flow<Invoice?> {
        return invoiceDao.getInvoiceByReservation(reservationId)
    }
    
    fun getInvoicesByPaymentStatus(isPaid: Boolean): Flow<List<Invoice>> {
        val status = if (isPaid) "PAID" else "UNPAID"
        return invoiceDao.getInvoicesByPaymentStatus(status)
    }
    
    fun getInvoicesByDateRange(startDate: Date, endDate: Date): Flow<List<Invoice>> {
        return invoiceDao.getInvoicesByDateRange(startDate, endDate)
    }
    
    fun getTotalRevenue(isPaid: Boolean = true): Flow<Double?> {
        val status = if (isPaid) "PAID" else "UNPAID"
        return invoiceDao.getTotalRevenue(status)
    }
    
    suspend fun updatePaymentStatus(invoiceId: Long, isPaid: Boolean, paymentDate: Date?, paymentMethod: String?) {
        val status = if (isPaid) "PAID" else "UNPAID"
        invoiceDao.updatePaymentStatus(invoiceId, status, paymentDate, paymentMethod)
    }
} 