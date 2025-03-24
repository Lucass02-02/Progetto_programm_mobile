package com.example.restaurantfinder.ui.screen.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantfinder.common.Resource
import com.example.restaurantfinder.domain.model.Hotel
import com.example.restaurantfinder.domain.use_case.GetHotelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ListUiState(
    val hotels: List<Hotel> = emptyList(),
    val loadingMsg: String? = null,
    val error: String? = null
)

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getHotelUseCase: GetHotelUseCase
): ViewModel() {


    var uiState by mutableStateOf(ListUiState())
        private set

    init {
        downloadHotels()
    }

    private fun downloadHotels() {
        viewModelScope.launch {
            getHotelUseCase().collect {resource ->
                when(resource) {
                    is Resource.Loading -> {
                        uiState = uiState.copy(
                            loadingMsg = resource.message,
                            error = null
                        )
                    }
                    is Resource.Success -> {
                        uiState = uiState.copy(
                            hotels = resource.data,
                            loadingMsg = null,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        uiState = uiState.copy(
                            loadingMsg = null,
                            error = resource.message
                        )
                    }

                }
            }
        }
    }



}