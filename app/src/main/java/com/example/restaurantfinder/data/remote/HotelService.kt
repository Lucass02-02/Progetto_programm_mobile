package com.example.restaurantfinder.data.remote

import com.example.restaurantfinder.common.API_DATA
import com.example.restaurantfinder.data.remote.model.RemoteHotel
import com.example.restaurantfinder.domain.model.Hotel
import retrofit2.http.GET

interface HotelService {
    @GET(API_DATA) // Assicurati che API_DATA sia definito
    suspend fun getHotels(): ApiResponse // Ora restituisce un oggetto ApiResponse
}