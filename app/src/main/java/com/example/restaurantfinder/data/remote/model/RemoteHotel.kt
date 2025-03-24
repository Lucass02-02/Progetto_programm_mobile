package com.example.restaurantfinder.data.remote.model

data class RemoteHotel(
    val Classificazione: String,
    val Denominazione: String,
    val Doppie: Int,
    val Indirizzo: String,
    val Municipio: String,
    val Posti_letto: Int,
    val Posti_letto_unita_abitative: Int,
    val Quadruple: Int,
    val Quintuple: Int,
    val Sestuple: Int,
    val Singole: Int,
    val Tipologia: String,
    val Totale_camere: Int,
    val Triple: Int,
    val Unita_abitative: Int
)