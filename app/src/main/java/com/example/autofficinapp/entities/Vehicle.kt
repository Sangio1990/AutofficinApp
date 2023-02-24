package com.example.autofficina.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.SET_NULL
import androidx.room.PrimaryKey

/*
Per ogni auto è necessario registrare
- Numero di telaio
- Marca
- Modello
- Targa
Per ogni auto sono possibili più interventi
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
