package com.example.autofficina.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.SET_NULL
import androidx.room.PrimaryKey

/* TODO: Questa è la consegna dataci
Per ogni auto è necessario registrare
- Numero di telaio
- Marca
- Modello
- Targa
Per ogni auto sono possibili più interventi
 */

/**

Rappresenta un veicolo associato ad un cliente dell'officina. Ogni veicolo è identificato da un ID generato automaticamente dal database.
I dati del veicolo includono il numero di telaio, la marca, il modello e la targa.
Il veicolo è associato ad un cliente tramite un ID di riferimento.
@property id l'ID del veicolo generato automaticamente dal database
@property chassisNumber il numero di telaio del veicolo
@property brand la marca del veicolo
@property model il modello del veicolo
@property plateNumber la targa del veicolo
@property clientId l'ID del cliente proprietario del veicolo a cui è associato il veicolo, null se non è associato ad alcun cliente
 */
@Entity(
    tableName = "vehicles",
    foreignKeys = [ForeignKey(
        entity = Client::class,
        parentColumns = ["id"],
        childColumns = ["client_id"],
        onDelete = SET_NULL
    )]
)
data class Vehicle(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val chassisNumber: String,
    val brand: String,
    val model: String,
    val plateNumber: String,
    @ColumnInfo(name = "client_id") val clientId: Long?
)
