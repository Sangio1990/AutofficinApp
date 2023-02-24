package com.example.autofficinapp.services

import com.example.autofficina.entities.Client
import com.example.autofficinapp.dao.ClientDao


/**
 * Questa classe ha la funzione di gestire imput ed aoutput della tabella "clients" dal database
 * salvando una copia dell'istanza del dao al suo interno e rendendola disponibile all'intera app
 */
interface IClientService {
    /**
     * funzione che setta il dao all'interno della classe
     * @param dao: Clientdao
     */
    fun setDao(dao: ClientDao)

    /**
     * funzione che aggiunge un cliente al database
     * @param client: Client
     */
    fun addClient(client: Client)

    /**
     * funzione che rimuove un cliente dal database
     * @param client: Client
     */
    fun removeClient(client: Client)

    /**
     * funzione che rimuove un cliente dal database
     * usando l'id come paramentro
     * @param id: Long
     */
    fun removeClient(id: Long)

    /**
     * funzione che restituisce tutti i clienti salvati nel database,
     * restituendoli sotto forma di lista
     * @return List<Client>
     */
    fun getAllClients(): List<Client>

    /**
     * funzione che restituisce un singolo cliente dal database
     * passandone l'id
     * @param id: Long -> Id del cliente
     * @return client: Client -> Istanza di tipo Client, null se non lo trova
     */
    fun getClient(id: Long): Client?
}