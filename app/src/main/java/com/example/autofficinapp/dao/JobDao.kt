package com.example.autofficinapp.dao

import com.example.autofficina.entities.Client
import com.example.autofficina.entities.Job

interface JobDao {
    /**
     * Salva un lavoro sul database.
     */
    fun save(job: Job)

    /**
     * Recupera i lavori dal database
     * @return i lavori salvati nel database.
     */
    fun getAll(): List<Job>

    /**
     * Cancella un lavoro dal database
     */
    fun delete(job: Job)

}
