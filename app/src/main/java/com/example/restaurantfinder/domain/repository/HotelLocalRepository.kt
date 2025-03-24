package com.example.restaurantfinder.domain.repository

import com.example.restaurantfinder.domain.model.Hotel
import kotlinx.coroutines.flow.Flow

interface HotelLocalRepository {

    suspend fun insert(hotel: Hotel)
    suspend fun insert(hotels: List<Hotel>)
    fun getAll(): Flow<List<Hotel>>
    suspend fun clearAll()
}