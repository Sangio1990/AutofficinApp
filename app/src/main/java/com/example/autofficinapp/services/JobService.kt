package com.example.autofficinapp.services


import com.example.autofficina.entities.Job
import com.example.autofficinapp.dao.JobDao
import java.util.concurrent.Executors

object JobService : IJobService {
    private lateinit var jobDao: JobDao

    override fun setDao(dao: JobDao) {
        jobDao = dao
    }

    override fun addJob(job: Job) {
        Executors.newSingleThreadExecutor().execute {
            jobDao.save(job)
        }
    }

    override fun removeJob(job: Job) {
        Executors.newSingleThreadExecutor().execute {
            jobDao.delete(job)
        }
    }

    override fun removeJob(id: Long) {
        Executors.newSingleThreadExecutor().execute {
            jobDao.delete(getAllJobs().first { c -> c.id == id })
        }
    }

    override fun getAllJobs(): List<Job> {
        var job_done = false
        var data: List<Job>? = null
        Executors.newSingleThreadExecutor().execute {
            data = jobDao.getAll()
            job_done = true
        }

        // attesa attiva
        while (!job_done) {
            // attendo 50 millisecondi prima di verificare nuovamente la variabile
            Thread.sleep(50)
        }

        return data ?: listOf(Job(0, "", 0f, "", "", 0))
    }
}
