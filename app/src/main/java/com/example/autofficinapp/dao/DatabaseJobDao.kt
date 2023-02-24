package com.example.autofficinapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.autofficina.entities.Job

@Dao
interface DatabaseJobDao: JobDao{
    @Query("SELECT * FROM jobs")
    override fun getAll(): List<Job>

    @Insert
    override fun save(job: Job)

    @Delete
    override fun delete(job: Job)
}
