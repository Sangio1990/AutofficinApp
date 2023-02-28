package com.example.autofficinapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.autofficina.entities.Job

/**
 * Data Access Object (DAO) che implementa l'interfaccia JobDao e utilizza le annotazioni Room per
 * fornire i metodi necessari per l'accesso ai dati relativi ai Lavori dal database.
 */
@Dao
interface DatabaseJobDao: JobDao{
    @Query("SELECT * FROM jobs")
    override fun getAll(): List<Job>

    @Insert
    override fun save(job: Job)

    @Delete
    override fun delete(job: Job)
}
