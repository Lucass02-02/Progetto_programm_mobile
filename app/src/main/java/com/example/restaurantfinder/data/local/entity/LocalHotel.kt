package com.example.restaurantfinder.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.restaurantfinder.data.local.Converters
import com.example.restaurantfinder.data.remote.model.Geometry
import com.example.restaurantfinder.data.remote.model.Photo

@Entity(tableName = "hotels")
@TypeConverters(Converters::class) // Per salvare liste e oggetti complessi
data class LocalHotel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val indirizzo: String,
    val classificazione: Double?,
    val denominazione: String,
    val foto: List<Photo>?,
    val posizione: Geometry,
    val icon: String,
    val iconBackgroundColor: String,
    val iconMaskBaseUri: String
)
