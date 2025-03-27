package com.example.hotelapp.utils

import com.example.hotelapp.data.model.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Proporciona datos de ejemplo para la aplicación del hotel
 */
object SampleDataProvider {

    /**
     * Genera una lista de habitaciones de ejemplo
     */
    fun getSampleRooms(): List<Room> {
        val roomList = mutableListOf<Room>()
        
        // Single rooms
        roomList.add(
            Room(
                id = 1,
                number = "101",
                type = RoomType.SINGLE,
                capacity = 1,
                pricePerNight = 80.0,
                status = RoomStatus.AVAILABLE,
                description = "Habitación individual con vistas al jardín",
                floor = 1,
                amenities = listOf("TV", "WiFi", "Baño privado", "Aire acondicionado")
            )
        )
        
        roomList.add(
            Room(
                id = 2,
                number = "102",
                type = RoomType.SINGLE,
                capacity = 1,
                pricePerNight = 85.0,
                status = RoomStatus.OCCUPIED,
                description = "Habitación individual con vistas a la ciudad",
                floor = 1,
                amenities = listOf("TV", "WiFi", "Baño privado", "Minibar", "Aire acondicionado")
            )
        )
        
        // Double rooms
        roomList.add(
            Room(
                id = 3,
                number = "201",
                type = RoomType.DOUBLE,
                capacity = 2,
                pricePerNight = 120.0,
                status = RoomStatus.AVAILABLE,
                description = "Habitación doble con cama king size y vistas al jardín",
                floor = 2,
                amenities = listOf("TV", "WiFi", "Baño privado", "Minibar", "Secador de pelo", "Aire acondicionado")
            )
        )
        
        roomList.add(
            Room(
                id = 4,
                number = "202",
                type = RoomType.DOUBLE,
                capacity = 2,
                pricePerNight = 130.0,
                status = RoomStatus.MAINTENANCE,
                description = "Habitación doble con dos camas individuales y vistas a la ciudad",
                floor = 2,
                amenities = listOf("TV", "WiFi", "Baño privado", "Minibar", "Secador de pelo", "Aire acondicionado")
            )
        )
        
        // Suite rooms
        roomList.add(
            Room(
                id = 5,
                number = "301",
                type = RoomType.SUITE,
                capacity = 2,
                pricePerNight = 200.0,
                status = RoomStatus.AVAILABLE,
                description = "Suite con dormitorio separado y sala de estar",
                floor = 3,
                amenities = listOf("TV", "WiFi", "Baño privado", "Jacuzzi", "Minibar", "Secador de pelo", "Aire acondicionado", "Cafetera")
            )
        )
        
        roomList.add(
            Room(
                id = 6,
                number = "302",
                type = RoomType.SUITE,
                capacity = 2,
                pricePerNight = 220.0,
                status = RoomStatus.OCCUPIED,
                description = "Suite con dormitorio separado, sala de estar y vistas panorámicas",
                floor = 3,
                amenities = listOf("TV", "WiFi", "Baño privado", "Jacuzzi", "Minibar", "Secador de pelo", "Aire acondicionado", "Cafetera", "Terraza")
            )
        )
        
        // Luxury rooms
        roomList.add(
            Room(
                id = 7,
                number = "401",
                type = RoomType.LUXURY,
                capacity = 2,
                pricePerNight = 350.0,
                status = RoomStatus.AVAILABLE,
                description = "Habitación de lujo con servicios premium y vistas panorámicas",
                floor = 4,
                amenities = listOf("TV", "WiFi", "Baño privado", "Jacuzzi", "Sauna", "Minibar", "Secador de pelo", "Aire acondicionado", "Cafetera", "Terraza", "Servicio de habitaciones 24h")
            )
        )
        
        // Family rooms
        roomList.add(
            Room(
                id = 8,
                number = "501",
                type = RoomType.FAMILY,
                capacity = 4,
                pricePerNight = 250.0,
                status = RoomStatus.AVAILABLE,
                description = "Habitación familiar con espacio para 4 personas",
                floor = 5,
                amenities = listOf("TV", "WiFi", "Baño privado", "Minibar", "Secador de pelo", "Aire acondicionado", "Cuna disponible")
            )
        )
        
        roomList.add(
            Room(
                id = 9,
                number = "502",
                type = RoomType.FAMILY,
                capacity = 5,
                pricePerNight = 280.0,
                status = RoomStatus.OCCUPIED,
                description = "Habitación familiar grande con capacidad para 5 personas y área de juegos",
                floor = 5,
                amenities = listOf("TV", "WiFi", "Baño privado", "Minibar", "Secador de pelo", "Aire acondicionado", "Cuna disponible", "Área de juegos")
            )
        )
        
        return roomList
    }

    /**
     * Genera una lista de clientes de ejemplo
     */
    fun getSampleCustomers(): List<Customer> {
        val customerList = mutableListOf<Customer>()
        
        customerList.add(
            Customer(
                id = 1,
                name = "Juan Pérez",
                email = "juan.perez@example.com",
                phone = "123-456-7890",
                address = "Calle Principal 123, Ciudad",
                documentNumber = "12345678A"
            )
        )
        
        customerList.add(
            Customer(
                id = 2,
                name = "María López",
                email = "maria.lopez@example.com",
                phone = "234-567-8901",
                address = "Avenida Central 456, Ciudad",
                documentNumber = "23456789B"
            )
        )
        
        customerList.add(
            Customer(
                id = 3,
                name = "Carlos Rodríguez",
                email = "carlos.rodriguez@example.com",
                phone = "345-678-9012",
                address = "Plaza Mayor 789, Ciudad",
                documentNumber = "34567890C"
            )
        )
        
        customerList.add(
            Customer(
                id = 4,
                name = "Ana Martínez",
                email = "ana.martinez@example.com",
                phone = "456-789-0123",
                address = "Calle Secundaria 101, Ciudad",
                documentNumber = "45678901D"
            )
        )
        
        customerList.add(
            Customer(
                id = 5,
                name = "Pedro Sánchez",
                email = "pedro.sanchez@example.com",
                phone = "567-890-1234",
                address = "Avenida Norte 202, Ciudad",
                documentNumber = "56789012E"
            )
        )
        
        return customerList
    }

    /**
     * Genera una lista de servicios de ejemplo
     */
    fun getSampleServices(): List<Service> {
        val serviceList = mutableListOf<Service>()
        
        serviceList.add(
            Service(
                id = 1,
                name = "Desayuno continental",
                description = "Desayuno completo con café, zumo, bollería y fruta",
                price = 15.0,
                category = ServiceCategory.FOOD
            )
        )
        
        serviceList.add(
            Service(
                id = 2,
                name = "Almuerzo buffet",
                description = "Buffet completo con opciones locales e internacionales",
                price = 25.0,
                category = ServiceCategory.FOOD
            )
        )
        
        serviceList.add(
            Service(
                id = 3,
                name = "Cena gourmet",
                description = "Cena de 3 platos con opciones a la carta",
                price = 35.0,
                category = ServiceCategory.FOOD
            )
        )
        
        serviceList.add(
            Service(
                id = 4,
                name = "Limpieza de habitación",
                description = "Servicio de limpieza adicional de la habitación",
                price = 20.0,
                category = ServiceCategory.CLEANING
            )
        )
        
        serviceList.add(
            Service(
                id = 5,
                name = "Servicio de lavandería",
                description = "Lavado y planchado de ropa",
                price = 15.0,
                category = ServiceCategory.CLEANING
            )
        )
        
        serviceList.add(
            Service(
                id = 6,
                name = "Masaje relajante",
                description = "Masaje corporal de 60 minutos",
                price = 70.0,
                category = ServiceCategory.WELLNESS
            )
        )
        
        serviceList.add(
            Service(
                id = 7,
                name = "Tratamiento facial",
                description = "Tratamiento facial hidratante de 45 minutos",
                price = 60.0,
                category = ServiceCategory.WELLNESS
            )
        )
        
        serviceList.add(
            Service(
                id = 8,
                name = "Traslado al aeropuerto",
                description = "Servicio de transporte al aeropuerto",
                price = 50.0,
                category = ServiceCategory.TRANSPORT
            )
        )
        
        serviceList.add(
            Service(
                id = 9,
                name = "Alquiler de bicicletas",
                description = "Alquiler de bicicletas por día",
                price = 20.0,
                category = ServiceCategory.ENTERTAINMENT
            )
        )
        
        serviceList.add(
            Service(
                id = 10,
                name = "Acceso a spa",
                description = "Acceso a instalaciones de spa por día",
                price = 30.0,
                category = ServiceCategory.WELLNESS
            )
        )
        
        return serviceList
    }

    /**
     * Genera una lista de reservas de ejemplo
     */
    fun getSampleReservations(): List<Reservation> {
        val reservationList = mutableListOf<Reservation>()
        
        // Reserva 1 - Checked in
        val checkInDate1 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -2)
        }.time
        
        val checkOutDate1 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 3)
        }.time
        
        reservationList.add(
            Reservation(
                id = 1,
                customerId = 1,
                roomId = 2,
                checkInDate = checkInDate1,
                checkOutDate = checkOutDate1,
                numberOfGuests = 1,
                totalPrice = 425.0, // 5 noches a 85.0
                status = ReservationStatus.CHECKED_IN
            )
        )
        
        // Reserva 2 - Confirmed (future)
        val checkInDate2 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 7)
        }.time
        
        val checkOutDate2 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 10)
        }.time
        
        reservationList.add(
            Reservation(
                id = 2,
                customerId = 2,
                roomId = 3,
                checkInDate = checkInDate2,
                checkOutDate = checkOutDate2,
                numberOfGuests = 2,
                totalPrice = 360.0, // 3 noches a 120.0
                status = ReservationStatus.CONFIRMED
            )
        )
        
        // Reserva 3 - Checked out
        val checkInDate3 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -10)
        }.time
        
        val checkOutDate3 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -5)
        }.time
        
        reservationList.add(
            Reservation(
                id = 3,
                customerId = 3,
                roomId = 5,
                checkInDate = checkInDate3,
                checkOutDate = checkOutDate3,
                numberOfGuests = 2,
                totalPrice = 1000.0, // 5 noches a 200.0
                status = ReservationStatus.CHECKED_OUT
            )
        )
        
        // Reserva 4 - Cancelled
        val checkInDate4 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 15)
        }.time
        
        val checkOutDate4 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 20)
        }.time
        
        reservationList.add(
            Reservation(
                id = 4,
                customerId = 4,
                roomId = 7,
                checkInDate = checkInDate4,
                checkOutDate = checkOutDate4,
                numberOfGuests = 2,
                totalPrice = 1750.0, // 5 noches a 350.0
                status = ReservationStatus.CANCELLED
            )
        )
        
        // Reserva 5 - Confirmed (current)
        val checkInDate5 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -1)
        }.time
        
        val checkOutDate5 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 4)
        }.time
        
        reservationList.add(
            Reservation(
                id = 5,
                customerId = 5,
                roomId = 9,
                checkInDate = checkInDate5,
                checkOutDate = checkOutDate5,
                numberOfGuests = 5,
                totalPrice = 1400.0, // 5 noches a 280.0
                status = ReservationStatus.CONFIRMED
            )
        )
        
        return reservationList
    }

    /**
     * Genera una lista de servicios de reserva de ejemplo
     */
    fun getSampleReservationServices(): List<ReservationService> {
        val reservationServiceList = mutableListOf<ReservationService>()
        
        // Servicios para la reserva 1
        val serviceDate1 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -1)
        }.time
        
        reservationServiceList.add(
            ReservationService(
                id = 1,
                reservationId = 1,
                serviceId = 1, // Desayuno continental
                quantity = 1,
                date = serviceDate1,
                price = 15.0
            )
        )
        
        reservationServiceList.add(
            ReservationService(
                id = 2,
                reservationId = 1,
                serviceId = 4, // Limpieza de habitación
                quantity = 1,
                date = serviceDate1,
                price = 20.0
            )
        )
        
        // Servicios para la reserva 3 (ya finalizada)
        val serviceDate3 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -8)
        }.time
        
        reservationServiceList.add(
            ReservationService(
                id = 3,
                reservationId = 3,
                serviceId = 1, // Desayuno continental
                quantity = 2,
                date = serviceDate3,
                price = 15.0
            )
        )
        
        reservationServiceList.add(
            ReservationService(
                id = 4,
                reservationId = 3,
                serviceId = 3, // Cena gourmet
                quantity = 2,
                date = serviceDate3,
                price = 35.0
            )
        )
        
        reservationServiceList.add(
            ReservationService(
                id = 5,
                reservationId = 3,
                serviceId = 6, // Masaje relajante
                quantity = 2,
                date = serviceDate3,
                price = 70.0
            )
        )
        
        reservationServiceList.add(
            ReservationService(
                id = 6,
                reservationId = 3,
                serviceId = 10, // Acceso a spa
                quantity = 2,
                date = serviceDate3,
                price = 30.0
            )
        )
        
        reservationServiceList.add(
            ReservationService(
                id = 7,
                reservationId = 3,
                serviceId = 8, // Traslado al aeropuerto
                quantity = 1,
                date = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -5) }.time,
                price = 50.0
            )
        )
        
        return reservationServiceList
    }

    /**
     * Genera una lista de facturas de ejemplo
     */
    fun getSampleInvoices(): List<Invoice> {
        val invoiceList = mutableListOf<Invoice>()
        
        // Factura para la reserva 3 (completada)
        val dueDate3 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -5)
        }.time
        
        val totalReservation3 = 1000.0 // Precio de la reserva
        val totalServices3 = (15.0 * 2) + (35.0 * 2) + (70.0 * 2) + (30.0 * 2) + 50.0 // Servicios
        val subtotal3 = totalReservation3 + totalServices3
        val tax3 = subtotal3 * 0.21 // 21% de IVA
        val total3 = subtotal3 + tax3
        
        invoiceList.add(
            Invoice(
                id = 1,
                reservationId = 3,
                customerId = 3,
                issueDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -5) }.time,
                dueDate = dueDate3,
                subtotal = subtotal3,
                tax = tax3,
                total = total3,
                paymentStatus = PaymentStatus.PAID,
                paymentMethod = PaymentMethod.CREDIT_CARD,
                paymentDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -5) }.time
            )
        )
        
        // Factura para la reserva 1 (en curso)
        val dueDate1 = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 5)
        }.time
        
        val totalReservation1 = 425.0 // Precio de la reserva
        val totalServices1 = 15.0 + 20.0 // Servicios hasta ahora
        val subtotal1 = totalReservation1 + totalServices1
        val tax1 = subtotal1 * 0.21 // 21% de IVA
        val total1 = subtotal1 + tax1
        
        invoiceList.add(
            Invoice(
                id = 2,
                reservationId = 1,
                customerId = 1,
                issueDate = Calendar.getInstance().time,
                dueDate = dueDate1,
                subtotal = subtotal1,
                tax = tax1,
                total = total1,
                paymentStatus = PaymentStatus.PENDING
            )
        )
        
        return invoiceList
    }
} 