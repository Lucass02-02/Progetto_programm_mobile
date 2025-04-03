package com.example.restaurantfinder.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteHotel(
    @SerializedName("business_status") val businessStatus: String,
    @SerializedName("formatted_address") val formattedAddress: String,
    @SerializedName("geometry") val geometry: Geometry,
    @SerializedName("icon") val icon: String,
    @SerializedName("icon_background_color") val iconBackgroundColor: String,
    @SerializedName("icon_mask_base_uri") val iconMaskBaseUri: String,
    @SerializedName("name") val name: String,
    @SerializedName("photos") val photos: List<Photo>?, // Può essere null
    @SerializedName("place_id") val placeId: String,
    @SerializedName("plus_code") val plusCode: PlusCode?, // Può essere null
    @SerializedName("rating") val rating: Double?, // Cambiato da String a Double
    @SerializedName("reference") val reference: String,
    @SerializedName("types") val types: List<String>,
    @SerializedName("user_ratings_total") val userRatingsTotal: Int
)
