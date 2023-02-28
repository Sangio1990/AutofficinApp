package com.example.autofficinapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.autofficina.entities.Client

/**
 * Data Access Object (DAO) che implementa l'interfaccia ClientDao e utilizza le annotazioni Room per
 * fornire i metodi necessari per l'accesso ai dati relativi ai Clienti dal database.
 */
@Dao
interface DatabaseClientsDao : ClientDao{
    @Query("SELECT * FROM clients")
    override fun getAll(): List<Client>

    @Insert
    override fun save(client: Client)

    @Delete
    override fun delete(client: Client)

    @Query("SELECT * FROM clients WHERE id = :id")
    override fun getClient(id: Long): Client
}


