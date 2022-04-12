package com.sopian.binarnetworking.data.source.remote

import com.sopian.binarnetworking.data.ApiResponse
import com.sopian.binarnetworking.data.source.remote.network.ApiService
import com.sopian.binarnetworking.data.source.remote.response.CarResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllCar(
        callback: (ApiResponse<List<CarResponse>?>) -> Unit
    ) {
        val client = apiService.getAllCar()

        client.enqueue(object : Callback<List<CarResponse>> {
            override fun onResponse(
                call: Call<List<CarResponse>>,
                response: Response<List<CarResponse>>
            ) {
                if (response.code() == 200 && response.isSuccessful) {
                    val data = response.body()
                    callback(ApiResponse.Success(data))
                }
            }

            override fun onFailure(call: Call<List<CarResponse>>, t: Throwable) {
                callback(ApiResponse.Error(t.message.toString()))
            }

        })
    }
}