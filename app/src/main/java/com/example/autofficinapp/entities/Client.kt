package com.example.autofficina.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/* TODO: Questa è la consegna dataci
Per ogni cliente è necessario registrare:
- nome e cognome
- numero di telefono
Ogni cliente può avere più auto
 */

/**
Rappresenta un cliente dell'officina. Ogni cliente è identificato da un ID generato automaticamente dal database.
I dati anagrafici del cliente includono il nome, il cognome, il numero di telefono e l'indirizzo email.
Un cliente può avere molteplici veicoli associati.
@property id l'ID del cliente generato automaticamente dal database
@property name il nome del cliente
@property surname il cognome del cliente
@property phoneNumber il numero di telefono del cliente
@property email l'indirizzo email del cliente
 */
@Entity(tableName = "clients")
data class Client(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val email: String
)
