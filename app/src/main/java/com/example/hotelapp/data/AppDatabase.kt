package com.example.hotelapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hotelapp.data.converters.DateConverter
import com.example.hotelapp.data.converters.RoomStatusConverter
import com.example.hotelapp.data.converters.RoomTypeConverter
import com.example.hotelapp.data.converters.ReservationStatusConverter
import com.example.hotelapp.data.converters.ServiceCategoryConverter
import com.example.hotelapp.data.dao.*
import com.example.hotelapp.data.entity.*
import com.example.hotelapp.utils.SampleDataProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        HotelRoom::class,
        Customer::class,
        Service::class,
        Reservation::class,
        ReservationService::class,
        Invoice::class
    ],
    version = 12,
    exportSchema = false
)
@TypeConverters(
    DateConverter::class,
    RoomStatusConverter::class,
    RoomTypeConverter::class,
    ReservationStatusConverter::class,
    ServiceCategoryConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun roomDao(): RoomDao
    abstract fun customerDao(): CustomerDao
    abstract fun serviceDao(): ServiceDao
    abstract fun reservationDao(): ReservationDao
    abstract fun reservationServiceDao(): ReservationServiceDao
    abstract fun invoiceDao(): InvoiceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Destruir base de datos si existe
                context.deleteDatabase("hotel_database")
                
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "hotel_database"
                )
                .fallbackToDestructiveMigration()
                .addCallback(HotelDatabaseCallback(scope))
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class HotelDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database)
                    }
                }
            }

            private suspend fun populateDatabase(database: AppDatabase) {
                // Insertar datos de muestra
                val sampleData = SampleDataProvider()
                
                // Insertar habitaciones
                database.roomDao().insertAll(sampleData.getSampleRooms())
                
                // Insertar clientes
                database.customerDao().insertAll(sampleData.getSampleCustomers())
                
                // Insertar servicios
                database.serviceDao().insertAll(sampleData.getSampleServices())
                
                // Insertar reservas
                database.reservationDao().insertAll(sampleData.getSampleReservations())
                
                // Insertar servicios de reserva
                database.reservationServiceDao().insertAll(sampleData.getSampleReservationServices())
                
                // Insertar facturas
                database.invoiceDao().insertAll(sampleData.getSampleInvoices())
            }
        }
    }
} 