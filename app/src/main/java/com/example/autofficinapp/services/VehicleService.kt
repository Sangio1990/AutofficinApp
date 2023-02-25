package com.example.autofficinapp.services

import com.example.autofficina.entities.Client
import com.example.autofficina.entities.Vehicle
import com.example.autofficinapp.dao.VehicleDao
import java.util.concurrent.Executors

object VehicleService : IVehicleService {
    private lateinit var vehicleDao: VehicleDao

    override fun setDao(dao: VehicleDao) {
        vehicleDao = dao
    }

    override fun addVehicle(vehicle: Vehicle) {
        Executors.newSingleThreadExecutor().execute {
            vehicleDao.save(vehicle)
        }
    }

    override fun removeVehicle(vehicle: Vehicle) {
        Executors.newSingleThreadExecutor().execute {
            vehicleDao.delete(vehicle)
        }
    }

    override fun removeVehicle(id: Long) {
        Executors.newSingleThreadExecutor().execute {
            vehicleDao.delete(getAllVehicles().first { c -> c.id == id })
        }
    }

    override fun getAllVehicles(): List<Vehicle> {
        var job_done = false
        var data: List<Vehicle>? = null
        Executors.newSingleThreadExecutor().execute {
            data = vehicleDao.getAll()
            job_done = true
        }

        // attesa attiva
        while (!job_done) {
            // attendo 50 millisecondi prima di verificare nuovamente la variabile
            Thread.sleep(50)
        }

        return data ?: listOf(Vehicle(0, "", "", "", "", 0))
    }

    override fun getVehicle(id: Long): Vehicle? {
        var job_done = false
        var data: Vehicle? = null
        Executors.newSingleThreadExecutor().execute {
            data = VehicleService.vehicleDao.getVehicle(id)
            job_done = true
        }
        // attesa attiva
        while (!job_done) {
            // attendo 50 millisecondi prima di verificare nuovamente la variabile
            Thread.sleep(50)
        }
        return data
    }
}
