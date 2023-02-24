package com.example.autofficinapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.autofficina.entities.Client
import com.example.autofficina.entities.Vehicle

/**
 * Dao con le annotazioni
 */
@Dao
interface DatabaseVehiclesDao : VehicleDao {
    @Query("SELECT * FROM vehicles")
    override fun getAll(): List<Vehicle>

    @Insert
    override fun save(vehicle: Vehicle)

    @Delete
    override fun delete(vehicle: Vehicle)

    @Query("SELECT * FROM vehicles WHERE id = :id")
    override fun getVehicle(id: Long): Vehicle
}