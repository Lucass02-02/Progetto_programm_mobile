package com.example.restaurantfinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restaurantfinder.data.local.dao.HotelDao
import com.example.restaurantfinder.data.local.entity.LocalHotel

@Database(entities = [LocalHotel::class], version = 1, exportSchema = false)
abstract class HotelDatabase: RoomDatabase() {

    abstract fun getHotelDao(): HotelDao
}