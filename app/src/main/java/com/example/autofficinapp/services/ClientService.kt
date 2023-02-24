package com.example.autofficinapp.services


import com.example.autofficina.entities.Client
import com.example.autofficinapp.dao.ClientDao
import java.util.concurrent.Executors

object ClientService : IClientService {
    private lateinit var clientDao: ClientDao

    override fun setDao(dao: ClientDao) {
        clientDao = dao
    }

    override fun addClient(client: Client) {
        Executors.newSingleThreadExecutor().execute {
            clientDao.save(client)
        }
    }

    override fun removeClient(client: Client) {
        Executors.newSingleThreadExecutor().execute {
            clientDao.delete(client)
        }
    }

    override fun removeClient(id: Long) {
        Executors.newSingleThreadExecutor().execute {
            clientDao.delete(getAllClients().first { c -> c.id == id })
        }
    }

    override fun getClient(id: Long): Client? {
        var job_done = false
        var data: Client? = null
        Executors.newSingleThreadExecutor().execute {
            data = clientDao.getClient(id)
            job_done = true
        }

        // attesa attiva
        while (!job_done) {
            // attendo 50 millisecondi prima di verificare nuovamente la variabile
            Thread.sleep(50)
        }

        return data
    }

    override fun getAllClients(): List<Client> {
        var job_done = false
        var data: List<Client>? = null
        Executors.newSingleThreadExecutor().execute {
            data = clientDao.getAll()
            job_done = true
        }

        // attesa attiva
        while (!job_done) {
            // attendo 50 millisecondi prima di verificare nuovamente la variabile
            Thread.sleep(50)
        }

        return data ?: listOf(Client(0, "", "", "", ""))
    }
}