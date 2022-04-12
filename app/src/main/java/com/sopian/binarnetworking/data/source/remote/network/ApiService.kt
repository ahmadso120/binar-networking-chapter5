package com.sopian.binarnetworking.data.source.remote.network

import com.sopian.binarnetworking.data.source.remote.response.CarResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("admin/car")
    fun getAllCar(): Call<List<CarResponse>>
}