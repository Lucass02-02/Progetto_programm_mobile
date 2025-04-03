// nel file HotelRetrofitRepository.kt
package com.example.restaurantfinder.data.remote.model

import com.example.restaurantfinder.data.remote.HotelService
import com.example.restaurantfinder.domain.model.Hotel
import com.example.restaurantfinder.domain.model.toModel // Importa il mapper
import com.example.restaurantfinder.domain.repository.HotelRemoteRepository // Importa l'interfaccia
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // È bene annotarla come Singleton se vuoi una sola istanza
class HotelRetrofitRepository @Inject constructor( // Assicurati che abbia @Inject constructor
    private val hotelService: HotelService
) : HotelRemoteRepository { // <-- Implementa l'interfaccia

    // Implementa il metodo dell'interfaccia
    override suspend fun getHotel(): List<Hotel> {
        val response = hotelService.getHotels()
        return response.results.map { it.toModel() } // Logica corretta di mapping
    }
}