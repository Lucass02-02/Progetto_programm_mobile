package com.example.restaurantfinder.di

import android.content.Context
import androidx.room.Room
import com.example.restaurantfinder.common.BASE_URL
import com.example.restaurantfinder.data.local.HotelDatabase
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

/* file con tutte le configurazioni dei dependecy Injection */

@Module  /* deve essere annotato con il module del dagger */
@InstallIn(SingletonComponent::class) /* dice come si comporta il modulo dentro l'applicazione*/

object RetrofitModule {

    @Provides /* perche deve essere chiamato in automatico */
    @Singleton /* perche devo avere un unica istanza del client */

    /* creazione retrofit client */

    fun retrofitClient(): Retrofit =        /* restituisce un istanza di retrofit cioè: */
        Retrofit.Builder()                  /*quello che il builder ci ritorna */
            .baseUrl(BASE_URL)              /* url a cui fa riferimento*/
            .addConverterFactory(GsonConverterFactory.create())         /* converter json siccome la risposta è un json */
            .build()


    @Provides
    @Singleton
    fun hotelService(retrofit: Retrofit): HotelService =  /* gli viene passato il client appena istanziato */
        retrofit.create(HotelService::class.java)   /*crea l hotel service */
}


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(hotelService: HotelService): HotelRemoteRepository {
        return object : HotelRemoteRepository {
            override suspend fun getHotel(): List<Hotel> {
                // Simuliamo una chiamata API tramite hotelService
                return hotelService.getHotels() // Assicurati che HotelService abbia questo metodo
            }
        }
    }

    @Provides
    @Singleton
    fun provideLocalRepository(database: HotelDatabase): HotelLocalRepository {
        return object : HotelLocalRepository {
            private val hotels = mutableListOf<Hotel>()

            override suspend fun insert(hotel: Hotel) {
                hotels.add(hotel)
            }

            override suspend fun insert(hotels: List<Hotel>) {
                this.hotels.addAll(hotels)
            }

            override fun getAll(): Flow<List<Hotel>> {
                return flow { emit(hotels) }
            }

            override suspend fun clearAll() {
                hotels.clear()
            }
        }
    }

   /* @Binds
    @Singleton
    abstract fun remoteRepository(repository: HotelRetrofitRepository): HotelRemoteRepository

    @Binds
    @Singleton
    abstract fun localRepository(repository: HotelLocalRepository): HotelLocalRepository */
}
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun database(@ApplicationContext context: Context): HotelDatabase =
        Room.databaseBuilder(context, HotelDatabase::class.java, "hotel_database").build()

    @Provides
    @Singleton
    fun hotelDao(database: HotelDatabase) = database.getHotelDao()
}