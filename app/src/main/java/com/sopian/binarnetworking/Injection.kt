package com.sopian.binarnetworking

import com.sopian.binarnetworking.data.CarRepository
import com.sopian.binarnetworking.data.ICarRepository
import com.sopian.binarnetworking.data.source.remote.RemoteDataSource
import com.sopian.binarnetworking.data.source.remote.network.ApiConfig

object Injection {
    fun provideRepository(): ICarRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())

        return CarRepository.getInstance(remoteDataSource)
    }
}