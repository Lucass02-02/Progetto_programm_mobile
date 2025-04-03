package com.example.restaurantfinder.ui.screen.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel()
) {
    val  uiState= viewModel.uiState

    GoogleMap(
        modifier= modifier
    )/*{
        uiState.Hotel.forEach{ hotel ->
            Marker(
                position= LatLng()
            )


        }*/

    }

