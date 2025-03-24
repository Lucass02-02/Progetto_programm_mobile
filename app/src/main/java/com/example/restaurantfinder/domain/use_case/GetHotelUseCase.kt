package com.example.restaurantfinder.domain.use_case

import com.example.restaurantfinder.common.Resource
import com.example.restaurantfinder.domain.model.Hotel
import com.example.restaurantfinder.domain.repository.HotelLocalRepository
import com.example.restaurantfinder.domain.repository.HotelRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetHotelUseCase @Inject constructor(
    private val remoteRepo: HotelRemoteRepository,
    private val localRepo: HotelLocalRepository
) {


    operator fun invoke(): Flow<Resource<List<Hotel>>> {
        return flow {
            emit(Resource.Loading("Loading..."))

            val localData = localRepo.getAll().firstOrNull() // Prendiamo la lista senza ascoltare i cambiamenti continui

            if (localData.isNullOrEmpty()) {
                try {
                    val data = remoteRepo.getHotel()
                    emit(Resource.Success(data)) // Emettiamo subito il successo, prima di salvarli

                    localRepo.insert(data) // Salviamo senza attivare un altro flow
                } catch (e: HttpException) {
                    emit(Resource.Error("Error ${e.message()}"))
                } catch (e: Exception) {
                    emit(Resource.Error("Generic Error ${e.message}"))
                }
            } else {
                emit(Resource.Success(localData))
            }

           /* localRepo.getAll()
                .catch { emit(Resource.Error("Error ${it.message}")) }
                .collect { list -> if(list.isEmpty()) {
                    //remote request
                    try {
                      val data =  remoteRepo.getHotel()
                      localRepo.insert(data)
                      emit(Resource.Success(data))

                    } catch (e: HttpException) {
                        emit(Resource.Error("Error ${e.message()}"))
                    }

                } else {
                    emit(Resource.Success(list))
                }} */
        }
    }
}