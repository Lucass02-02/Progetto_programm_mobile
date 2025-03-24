package com.example.restaurantfinder.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Hotel")
data class LocalHotel(
    @PrimaryKey
    val Denominazione: String,


    val Tipologia: String,
    val Classificazione: String,
    val Indirizzo: String,
    val Posti_letto: Int,
    val Totale_camere: Int,
)
