package com.example.restaurantfinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.restaurantfinder.data.local.dao.HotelDao
import com.example.restaurantfinder.data.local.entity.LocalHotel

@Database(entities = [LocalHotel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HotelDatabase: RoomDatabase() {

    abstract fun getHotelDao(): HotelDao
}