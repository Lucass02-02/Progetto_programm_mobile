package com.example.restaurantfinder.data.local

import com.example.restaurantfinder.data.local.dao.HotelDao
import com.example.restaurantfinder.data.local.entity.LocalHotel
import com.example.restaurantfinder.domain.model.Hotel
import com.example.restaurantfinder.domain.repository.HotelLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


fun LocalHotel.toModel(): Hotel = Hotel(
    Tipologia = Tipologia,
    Classificazione = Classificazione,
    Denominazione = Denominazione,
    Indirizzo = Indirizzo,
    Posti_letto = Posti_letto,
    Totale_camere = Totale_camere
)




fun Hotel.toLocal(): LocalHotel = LocalHotel(
    Tipologia = Tipologia,
    Classificazione = Classificazione,
    Denominazione = Denominazione,
    Indirizzo = Indirizzo,
    Posti_letto = Posti_letto,
    Totale_camere = Totale_camere
)

class HotelRoomRepository @Inject constructor(private val hotelDao: HotelDao): HotelLocalRepository {
    override suspend fun insert(hotel: Hotel) {
        hotelDao.insert(hotel.toLocal())
    }

    override suspend fun insert(hotels: List<Hotel>) {
        hotelDao.insert(hotels.map(Hotel::toLocal))
    }

    override fun getAll(): Flow<List<Hotel>> {
        return hotelDao.getAll().map { list -> list.map(LocalHotel::toModel) }
    }

    override suspend fun clearAll() {
        hotelDao.deleteAll()
    }
}