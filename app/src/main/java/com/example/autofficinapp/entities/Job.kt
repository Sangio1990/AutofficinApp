package com.example.autofficina.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.SET_NULL
import androidx.room.PrimaryKey
import java.util.Date

/* TODO: Questa è la consegna dataci
Per ogni intervento è necessario registrare
- Descrizione
- Durata oraria
- Data di arrivo
- Data di consegna
 */

/**

Rappresenta un lavoro eseguito su un veicolo. Ogni lavoro è identificato da un ID generato automaticamente dal database.
I dati del lavoro includono la descrizione del lavoro, le ore lavorative, la data di inizio e fine lavoro nel formato "dd-MM-yyyy" e l'ID del veicolo su cui è stato eseguito il lavoro.
@property id l'ID del lavoro generato automaticamente dal database
@property description la descrizione del lavoro
@property workHours le ore lavorative impiegate per il lavoro
@property startDate la data di inizio lavoro nel formato "dd-MM-yyyy"
@property endDate la data di fine lavoro nel formato "dd-MM-yyyy"
@property carId l'ID del veicolo su cui è stato eseguito il lavoro
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
