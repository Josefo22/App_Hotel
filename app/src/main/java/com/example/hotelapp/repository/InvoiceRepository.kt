package com.example.hotelapp.repository

import androidx.lifecycle.LiveData
import com.example.hotelapp.data.dao.InvoiceDao
import com.example.hotelapp.data.model.Invoice
import com.example.hotelapp.data.model.PaymentMethod
import com.example.hotelapp.data.model.PaymentStatus
import java.util.Date

class InvoiceRepository(private val invoiceDao: InvoiceDao) {
    
    val allInvoices: LiveData<List<Invoice>> = invoiceDao.getAllInvoices()
    
    suspend fun insert(invoice: Invoice): Long {
        return invoiceDao.insertInvoice(invoice)
    }
    
    suspend fun update(invoice: Invoice) {
        invoiceDao.updateInvoice(invoice)
    }
    
    suspend fun delete(invoice: Invoice) {
        invoiceDao.deleteInvoice(invoice)
    }
    
    fun getInvoiceById(invoiceId: Long): LiveData<Invoice> {
        return invoiceDao.getInvoiceById(invoiceId)
    }
    
    fun getInvoiceByReservation(reservationId: Long): LiveData<Invoice> {
        return invoiceDao.getInvoiceByReservation(reservationId)
    }
    
    fun getOverdueInvoices(today: Date): LiveData<List<Invoice>> {
        return invoiceDao.getOverdueInvoices(today, PaymentStatus.PAID)
    }
    
    fun getInvoicesByPaymentStatus(status: PaymentStatus): LiveData<List<Invoice>> {
        return invoiceDao.getInvoicesByPaymentStatus(status)
    }
    
    suspend fun updateInvoicePayment(invoiceId: Long, status: PaymentStatus, method: PaymentMethod, date: Date) {
        invoiceDao.updateInvoicePayment(invoiceId, status, method, date)
    }
} 