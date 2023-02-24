package com.example.autofficinapp.services

import com.example.autofficina.entities.Client
import com.example.autofficina.entities.Vehicle
import com.example.autofficinapp.dao.ClientDao
import com.example.autofficinapp.dao.VehicleDao

/**
 * Questa classe ha la funzione di gestire imput ed aoutput della tabella "vehicles" dal database
 * salvando una copia dell'istanza del dao al suo interno e rendendola disponibile all'intera app
 */

interface IVehicleService {

    /**
     * funzione che setta il dao all'interno della classe
     * @param dao: VehicleDao
     */
    fun setDao(dao: VehicleDao)

    /**
     * funzione che aggiunge un veicolo al database
     * @param vehicle: Vehicle
     */
    fun addVehicle(vehicle: Vehicle)

    /**
     * funzione che rimuove un veicolo dal database
     * @param vehicle: Vehicle
     */
    fun removeVehicle(vehicle: Vehicle)

    /**
     * funzione che rimuove un veicolo dal database
     * usando l'id come paramentro
     * @param id: Long
     */
    fun removeVehicle(id: Long)

    /**
     * funzione che restituisce tutti i clienti salvati nel database,
     * restituendoli sotto forma di lista
     * @return List<Vehicle>
     */
    fun getAllVehicles(): List<Vehicle>

    /**
     * funzione che restituisce un singolo veicolo dal database
     * passandone l'id
     * @param id: Long -> Id del veicolo
     * @return vehicle: Vehicle -> Istanza di tipo Vehicle
     */
    fun getVehicle(it: Long): Vehicle?
}
