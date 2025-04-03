package com.example.restaurantfinder.ui.screen.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantfinder.common.Resource
import com.example.restaurantfinder.domain.model.Hotel
import com.example.restaurantfinder.domain.use_case.GetHotelUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

data class  MapUIState (
val Hotel: List<Hotel> = emptyList(),
    val  loadingMsg: String? =null,
    val  error: String? = null,
)

class MapViewModel @Inject constructor(
    private val  getHotelUseCase: GetHotelUseCase,
)
    : ViewModel (){

        var  uiState by mutableStateOf(MapUIState())
            private set
    init {
        getHotel()
    }

    private fun getHotel(){

        viewModelScope.launch{
            getHotelUseCase().collect { resource ->
                uiState = when(resource) {
                    is Resource.Loading ->{
                        uiState.copy(
                            loadingMsg = resource.message,
                            error = null,
                        )
                    }
                    is Resource.Success -> {
                        uiState.copy(
                            Hotel = resource.data,
                            loadingMsg = null,
                            error = null,
                        )
                    }
                    is Resource.Error -> {
                        uiState.copy(
                            loadingMsg = null,
                            error =  resource.message,
                        )
                    }
                }
            }
        }
    }
}