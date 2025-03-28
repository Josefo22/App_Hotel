package com.example.hotelapp.utils

import com.example.hotelapp.data.entity.Customer
import com.example.hotelapp.data.entity.HotelRoom
import com.example.hotelapp.data.entity.Invoice
import com.example.hotelapp.data.entity.Reservation
import com.example.hotelapp.data.entity.ReservationService
import com.example.hotelapp.data.entity.Service
import com.example.hotelapp.data.model.ReservationStatus
import com.example.hotelapp.data.model.RoomStatus
import com.example.hotelapp.data.model.RoomType
import com.example.hotelapp.data.model.ServiceCategory
import java.util.*

/**
 * Proporciona datos de ejemplo para la aplicación del hotel
 */
class SampleDataProvider {

    /**
     * Genera una lista de habitaciones de ejemplo
     */
    fun getSampleRooms(): List<HotelRoom> {
        return listOf(
            HotelRoom(
                id = 1,
                number = "101",
                floor = 1,
                type = RoomType.STANDARD,
                pricePerNight = 100.0,
                capacity = 2,
                status = RoomStatus.AVAILABLE,
                description = "Habitación estándar con vistas al jardín",
                hasWifi = true,
                hasTV = true,
                hasAC = true,
                hasMinibar = false,
                imageUrl = "https://example.com/images/room101.jpg"
            ),
            HotelRoom(
                id = 2,
                number = "102",
                floor = 1,
                type = RoomType.STANDARD,
                pricePerNight = 100.0,
                capacity = 2,
                status = RoomStatus.AVAILABLE,
                description = "Habitación estándar con vistas al jardín",
                hasWifi = true,
                hasTV = true,
                hasAC = true,
                hasMinibar = false,
                imageUrl = "https://example.com/images/room102.jpg"
            ),
            HotelRoom(
                id = 3,
                number = "201",
                floor = 2,
                type = RoomType.DELUXE,
                pricePerNight = 150.0,
                capacity = 3,
                status = RoomStatus.AVAILABLE,
                description = "Habitación deluxe con vistas al mar",
                hasWifi = true,
                hasTV = true,
                hasAC = true,
                hasMinibar = true,
                imageUrl = "https://example.com/images/room201.jpg"
            ),
            HotelRoom(
                id = 4,
                number = "301",
                floor = 3,
                type = RoomType.SUITE,
                pricePerNight = 250.0,
                capacity = 4,
                status = RoomStatus.AVAILABLE,
                description = "Suite con sala de estar y vistas panorámicas",
                hasWifi = true,
                hasTV = true,
                hasAC = true,
                hasMinibar = true,
                imageUrl = "https://example.com/images/room301.jpg"
            )
        )
    }

    /**
     * Genera una lista de clientes de ejemplo
     */
    fun getSampleCustomers(): List<Customer> {
        return listOf(
            Customer(
                id = 1,
                name = "Juan Pérez",
                email = "juan.perez@example.com",
                phone = "123-456-7890",
                address = "Calle Principal 123, Ciudad",
                documentNumber = "12345678A",
                notes = "Cliente habitual"
            ),
            Customer(
                id = 2,
                name = "María García",
                email = "maria.garcia@example.com",
                phone = "987-654-3210",
                address = "Avenida Central 456, Ciudad",
                documentNumber = "87654321B",
                notes = "Prefiere habitaciones con vistas"
            )
        )
    }

    /**
     * Genera una lista de servicios de ejemplo
     */
    fun getSampleServices(): List<Service> {
        return listOf(
            Service(
                id = 1,
                name = "Desayuno buffet",
                description = "Buffet completo con opciones internacionales",
                price = 15.0,
                category = ServiceCategory.FOOD
            ),
            Service(
                id = 2,
                name = "Limpieza adicional",
                description = "Servicio de limpieza extra",
                price = 25.0,
                category = ServiceCategory.CLEANING
            ),
            Service(
                id = 3,
                name = "Spa completo",
                description = "Acceso a todas las instalaciones del spa",
                price = 50.0,
                category = ServiceCategory.WELLNESS
            ),
            Service(
                id = 4,
                name = "Traslado aeropuerto",
                description = "Servicio de traslado desde/hacia el aeropuerto",
                price = 30.0,
                category = ServiceCategory.TRANSPORT
            )
        )
    }

    /**
     * Genera una lista de reservas de ejemplo
     */
    fun getSampleReservations(): List<Reservation> {
        val calendar = Calendar.getInstance()
        val now = calendar.time
        
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val tomorrow = calendar.time
        
        calendar.add(Calendar.DAY_OF_MONTH, 5)
        val checkOutDate = calendar.time
        
        calendar.add(Calendar.DAY_OF_MONTH, -10)
        val pastDate = calendar.time
        
        calendar.add(Calendar.DAY_OF_MONTH, 5)
        val pastCheckOutDate = calendar.time
        
        return listOf(
            Reservation(
                id = 1,
                customerId = 1,
                roomId = 1,
                checkInDate = tomorrow,
                checkOutDate = checkOutDate,
                numberOfGuests = 2,
                totalPrice = 500.0,
                status = ReservationStatus.CONFIRMED,
                createdAt = now,
                notes = "Llegada tardía, después de las 10pm"
            ),
            Reservation(
                id = 2,
                customerId = 2,
                roomId = 3,
                checkInDate = pastDate,
                checkOutDate = pastCheckOutDate,
                numberOfGuests = 3,
                totalPrice = 750.0,
                status = ReservationStatus.COMPLETED,
                createdAt = pastDate,
                notes = "Incluye servicio de desayuno"
            )
        )
    }

    /**
     * Genera una lista de servicios de reserva de ejemplo
     */
    fun getSampleReservationServices(): List<ReservationService> {
        return listOf(
            ReservationService(
                id = 1,
                reservationId = 1,
                serviceId = 1,
                quantity = 2,
                date = Date(),
                price = 30.0
            ),
            ReservationService(
                id = 2,
                reservationId = 1,
                serviceId = 4,
                quantity = 1,
                date = Date(),
                price = 30.0
            ),
            ReservationService(
                id = 3,
                reservationId = 2,
                serviceId = 1,
                quantity = 3,
                date = Date(),
                price = 45.0
            ),
            ReservationService(
                id = 4,
                reservationId = 2,
                serviceId = 3,
                quantity = 1,
                date = Date(),
                price = 50.0
            )
        )
    }

    /**
     * Genera una lista de facturas de ejemplo
     */
    fun getSampleInvoices(): List<Invoice> {
        val calendar = Calendar.getInstance()
        val now = calendar.time
        
        calendar.add(Calendar.DAY_OF_MONTH, 15)
        val dueDate = calendar.time
        
        calendar.add(Calendar.DAY_OF_MONTH, -20)
        val pastDate = calendar.time
        
        calendar.add(Calendar.DAY_OF_MONTH, 10)
        val pastDueDate = calendar.time
        
        calendar.add(Calendar.DAY_OF_MONTH, 2)
        val paymentDate = calendar.time
        
        val invoice1 = Invoice()
        invoice1.id = 1
        invoice1.reservationId = 1
        invoice1.customerId = 1
        invoice1.issueDate = now
        invoice1.dueDate = dueDate
        invoice1.subtotal = 560.0
        invoice1.tax = 112.0
        invoice1.total = 672.0
        invoice1.paymentStatus = "UNPAID"
        
        invoice1.invoiceNumber = "INV-2023-001"
        invoice1.roomCharge = 500.0
        invoice1.serviceCharge = 60.0
        invoice1.taxAmount = 112.0
        invoice1.totalAmount = 672.0
        
        val invoice2 = Invoice()
        invoice2.id = 2
        invoice2.reservationId = 2
        invoice2.customerId = 2
        invoice2.issueDate = pastDate
        invoice2.dueDate = pastDueDate
        invoice2.subtotal = 845.0
        invoice2.tax = 169.0
        invoice2.total = 1014.0
        invoice2.paymentStatus = "PAID"
        invoice2.paymentDate = paymentDate
        invoice2.paymentMethod = "Tarjeta de Crédito"
        
        invoice2.invoiceNumber = "INV-2023-002"
        invoice2.roomCharge = 750.0
        invoice2.serviceCharge = 95.0
        invoice2.taxAmount = 169.0
        invoice2.totalAmount = 1014.0
        invoice2.isPaid = true
        
        return listOf(invoice1, invoice2)
    }
} 