package com.example.restaurantfinder.domain.repository

import com.example.restaurantfinder.domain.model.Hotel

interface HotelRemoteRepository {
    suspend fun getHotel(): List<Hotel>
}