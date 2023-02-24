package com.example.autofficinapp.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.autofficina.entities.Client
import com.example.autofficina.entities.Job
import com.example.autofficina.entities.Vehicle
import com.example.autofficinapp.dao.DatabaseClientsDao
import com.example.autofficinapp.dao.DatabaseJobDao
import com.example.autofficinapp.dao.DatabaseVehiclesDao

/**
 * Classe astratta che ha il compito di gestire il database
 *
 */
@Database(
    version = GarageDatabase.VERSION,
    entities = [Client::class, Vehicle::class, Job::class]
)
abstract class GarageDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 3
        const val DATABASE_NAME = "officina_database"

        @Volatile
        private var INSTANCE: GarageDatabase? = null

        /**
         * Funzione che crea un'istanza del database rendendolo un singleton
         */
        fun getDatabase(context: Context): GarageDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GarageDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

    /**
     * Funzione che restituisce l'istanza della tabella clients
     */
    abstract fun getClientDao(): DatabaseClientsDao
    /**
     * Funzione che restituisce l'istanza della tabella jobs
     */
    abstract fun getVehicleDao(): DatabaseVehiclesDao
    /**
     * Funzione che restituisce l'istanza della tabella vehicles
     */
    abstract fun getJobDao(): DatabaseJobDao
}