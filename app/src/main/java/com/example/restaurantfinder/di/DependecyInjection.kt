package com.example.restaurantfinder.di

import android.content.Context
import androidx.room.Room
import com.example.restaurantfinder.common.BASE_URL
import com.example.restaurantfinder.data.local.HotelDatabase
// Rimuovi import non usato: import com.example.restaurantfinder.data.remote.ApiResponse
import com.example.restaurantfinder.data.remote.HotelService
import com.example.restaurantfinder.data.remote.model.HotelRetrofitRepository
import com.example.restaurantfinder.domain.model.Hotel
import com.example.restaurantfinder.domain.repository.HotelLocalRepository
import com.example.restaurantfinder.domain.repository.HotelRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/* file con tutte le configurazioni dei dependency Injection */

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun retrofitClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun hotelService(retrofit: Retrofit): HotelService =
        retrofit.create(HotelService::class.java)
}


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule { // Mantiene 'abstract class'

    // --- Metodi @Binds ---
    // Questi rimangono direttamente nell'abstract class

    @Binds
    @Singleton
    abstract fun bindRemoteRepository(
        repository: HotelRetrofitRepository // Assicurati che HotelRetrofitRepository abbia @Inject constructor
    ): HotelRemoteRepository

    // Questo @Binds è commentato - lascialo così a meno che tu non crei
    // una classe concreta (es. HotelRoomRepository) per il db locale.
    /*
    @Binds
    @Singleton
    abstract fun bindLocalRepository(repository: HotelRoomRepository): HotelLocalRepository
    */

    // --- Metodi @Provides ---
    // Questi vanno nel companion object

    companion object {

        @Provides
        @Singleton
        fun provideLocalRepository(database: HotelDatabase): HotelLocalRepository {
            // IMPORTANTE: Fornisci HotelLocalRepository solo UNA volta.
            // Se usi questo @Provides, assicurati che il @Binds bindLocalRepository sopra sia commentato.
            // Questa è l'implementazione MOCK (in memoria):
            return object : HotelLocalRepository {
                private val hotels = mutableListOf<Hotel>()
                override suspend fun insert(hotel: Hotel) { hotels.add(hotel) }
                override suspend fun insert(hotels: List<Hotel>) { this.hotels.addAll(hotels) }
                override fun getAll(): Flow<List<Hotel>> { return flow { emit(hotels) } }
                override suspend fun clearAll() { hotels.clear() }
            }
        }

        // Altri metodi @Provides per questo modulo andrebbero qui...
    }
}

// ELIMINA QUESTA PARTE DUPLICATA CHE ERA QUI SOTTO RepositoryModule


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun database(@ApplicationContext context: Context): HotelDatabase =
        Room.databaseBuilder(context, HotelDatabase::class.java, "hotel_database").build()

    @Provides
    @Singleton
    fun hotelDao(database: HotelDatabase) = database.getHotelDao() // Giusto: fornisce HotelDao
}