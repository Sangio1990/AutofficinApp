package com.example.autofficinapp.dao

import com.example.autofficina.entities.Client

interface ClientDao {
    /**
     * Salva un cliente sul database.
     */
    fun save(client: Client)

    /**
     * Recupera i clienti dal database
     * @return le persone salvate nel database.
     */
    fun getAll(): List<Client>

    /**
     * Cancella un cliente dal database
     */
    fun delete(client: Client)

    /**
     * Recupera un singolo cliente dato un certo id
     */
    fun getClient(id: Long): Client

}
