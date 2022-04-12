package com.sopian.binarnetworking.data

import com.sopian.binarnetworking.DataMapper
import com.sopian.binarnetworking.data.source.remote.RemoteDataSource
import com.sopian.binarnetworking.model.Car

interface ICarRepository {
    fun getAllCar(callback: (Result<List<Car>?>) -> Unit)
}

class CarRepository private constructor(
    private val remoteDataSource: RemoteDataSource
) : ICarRepository {

    companion object {
        @Volatile
        private var instance: CarRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource
        ): CarRepository =
            instance ?: synchronized(this) {
                instance ?: CarRepository(remoteData)
            }
    }

    override fun getAllCar(callback: (Result<List<Car>?>) -> Unit) {
        remoteDataSource.getAllCar {

            when(it) {
                is ApiResponse.Success -> {
                    callback(Result.Success(DataMapper.mapResponsesToModel(it.data)))
                }
                is ApiResponse.Error -> {
                    callback(Result.Error(it.errorMessage))
                }
            }
        }
    }
}