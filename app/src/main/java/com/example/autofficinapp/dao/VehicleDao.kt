package com.example.autofficinapp.dao

import com.example.autofficina.entities.Client
import com.example.autofficina.entities.Vehicle

interface VehicleDao {
    /**
     * Salva un veicolo sul database.
     */
    fun save(vehicle: Vehicle)

    /**
     * Recupera i veicoli dal database
     * @return i veicoli salvati nel database.
     */
    fun getAll(): List<Vehicle>

    /**
     * Cancella un veicolo dal database
     */
    fun delete(vehicle: Vehicle)

    /**
     * Recupera un singolo veicolo dato un certo id
     */
    fun getVehicle(id: Long): Vehicle

}
