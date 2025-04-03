package com.example.restaurantfinder.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.restaurantfinder.data.local.entity.LocalHotel
import kotlinx.coroutines.flow.Flow


@Dao
interface HotelDao {

    @Upsert
    suspend fun insert(hotel: LocalHotel)

    @Upsert
    suspend fun insert(hotel: List<LocalHotel>)

    @Query("SELECT * FROM hotels ORDER BY Classificazione, Denominazione ASC")
    fun getAll(): Flow<List<LocalHotel>>

    @Query("DELETE FROM hotels")
    suspend fun deleteAll()
}