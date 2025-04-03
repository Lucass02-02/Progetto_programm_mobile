package com.example.restaurantfinder.data.remote

import com.example.restaurantfinder.common.API_DATA
import com.example.restaurantfinder.data.remote.model.RemoteHotel
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class ApiResponse(
    @SerializedName("results") val results: List<RemoteHotel>
)
