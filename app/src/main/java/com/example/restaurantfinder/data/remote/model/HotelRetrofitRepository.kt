package com.example.restaurantfinder.data.remote.model

import com.example.restaurantfinder.data.remote.HotelService
import com.example.restaurantfinder.domain.model.Hotel
import com.example.restaurantfinder.domain.repository.HotelRemoteRepository
import org.jetbrains.annotations.ApiStatus.Internal
import javax.inject.Inject

fun RemoteHotel.toModel(): Hotel = Hotel(

    Tipologia = Tipologia,
    Classificazione = Classificazione,
    Denominazione = Denominazione,
    Indirizzo = Indirizzo,
    Posti_letto = Posti_letto,
    Totale_camere = Totale_camere
)


class HotelRetrofitRepository @Inject constructor(private val hotelService: HotelService): HotelRemoteRepository {
    override suspend fun getHotel(): List<Hotel> {
        return hotelService.downloadData().map(RemoteHotel::toModel)
    }

}