package com.example.autofficinapp.services

import com.example.autofficina.entities.Job
import com.example.autofficinapp.dao.JobDao

/**
 * Questa classe ha la funzione di gestire imput ed aoutput della tabella "jobs" dal database
 * salvando una copia dell'istanza del dao al suo interno e rendendola disponibile all'intera app
 */
interface IJobService {
    /**
     * funzione che setta il dao all'interno della classe
     *
     * @param dao: JobDao
     */
    fun setDao(dao: JobDao)

    /**
     * funzione che aggiunge un lavoro al database
     *
     * @param job: Job
     */
    fun addJob(job: Job)

    /**
     * funzione che rimuove un lavoro dal database
     *
     * @param job: Job
     */
    fun removeJob(job: Job)

    /**
     * funzione che rimuove un lavoro dal database
     * usando l'id come paramentro
     *
     * @param id: Long
     */
    fun removeJob(id:Long)

    /**
     * funzione che restituisce tutti i lavori salvati nel database,
     * restituendoli sotto forma di lista
     *
     * @return List<Job>
     */
    fun getAllJobs():List<Job>
}

