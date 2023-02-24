package com.example.autofficina.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Per ogni cliente è necessario registrare:
- nome e cognome
- numero di telefono
Ogni cliente può avere più auto
 */
@Entity(tableName = "clients")
data class Client(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val email: String
)
