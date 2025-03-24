package com.example.restaurantfinder.data.remote

import com.example.restaurantfinder.common.API_DATA
import com.example.restaurantfinder.data.remote.model.RemoteHotel
import com.example.restaurantfinder.domain.model.Hotel
import retrofit2.http.GET

interface HotelService {

    @GET(API_DATA)
    suspend fun downloadData(): List<RemoteHotel>
    @GET(API_DATA)
    suspend fun getHotels(): List<Hotel>


}