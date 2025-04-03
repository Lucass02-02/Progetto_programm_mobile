package com.example.restaurantfinder.ui.screen.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restaurantfinder.domain.model.Hotel

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState

    if (uiState.loadingMsg != null) {
        Box(modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(text = uiState.loadingMsg)
        }
        return
    }

    if (uiState.error != null) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(text = uiState.error)
        }
        return
    }

    Column(modifier = modifier) {
        Text(
            modifier=Modifier.padding(16.dp),
            text = "Hotels",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(uiState.hotels.size){ index ->
                val hotel = uiState.hotels[index]
                HotelItem(
                    modifier = Modifier.fillMaxWidth(),
                    hotel = hotel,
                    onItemClick = {
                        // TODO: handle item click
                    }
                )
            }
        }
    }

}


@Composable
fun HotelItem(
    modifier: Modifier,
    hotel: Hotel,
    onItemClick: (Hotel) -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .clickable {
                onItemClick(hotel)
            }
    ) {
        Text(
            text = hotel.denominazione.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "${hotel.indirizzo}, ${hotel.classificazione}, ${hotel.denominazione}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}