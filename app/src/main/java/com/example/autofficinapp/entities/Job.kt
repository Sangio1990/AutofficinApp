package com.example.autofficina.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.SET_NULL
import androidx.room.PrimaryKey
import java.util.Date

/*
Per ogni intervento è necessario registrare
- Descrizione
- Durata oraria
- Data di arrivo
- Data di consegna
 */

@Entity(
    tableName = "jobs",
    foreignKeys = [ForeignKey(
        entity = Vehicle::class,
        parentColumns = ["id"],
        childColumns = ["vehicle_id"],
        onDelete = SET_NULL
    )]
)
data class Job(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val description: String,
    val workHours: Float,
    val startDate: String,
    val endDate: String,
    @ColumnInfo(name = "vehicle_id") val carId: Long?
)
