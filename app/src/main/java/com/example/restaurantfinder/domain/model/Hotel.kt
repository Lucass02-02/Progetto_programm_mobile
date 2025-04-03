package com.example.restaurantfinder.domain.model

import com.example.restaurantfinder.data.remote.model.Geometry
import com.example.restaurantfinder.data.remote.model.Photo
import com.example.restaurantfinder.data.remote.model.RemoteHotel

data class Hotel(
    val indirizzo: String,
    val classificazione: Double?,  // Cambiato da String a Double?
    val denominazione: String,
    val foto: List<Photo>?,  // Può essere nullo
    val posizione: Geometry,
    val icon: String,
    val iconBackgroundColor: String,
    val iconMaskBaseUri: String


)

fun RemoteHotel.toModel(): Hotel {
    return Hotel(
        indirizzo = this.formattedAddress, // Mappa formattedAddress a indirizzo
        classificazione = this.rating,      // Mappa rating a classificazione (entrambi Double?)
        denominazione = this.name,          // Mappa name a denominazione
        foto = this.photos,                 // Mappa photos a foto (entrambi List<Photo>?)
        posizione = this.geometry,          // Mappa geometry a posizione (entrambi Geometry)
        icon = this.icon,                   // Mappa icon
        iconBackgroundColor = this.iconBackgroundColor, // Mappa iconBackgroundColor
        iconMaskBaseUri = this.iconMaskBaseUri          // Mappa iconMaskBaseUri
    )
}