package com.sopian.binarnetworking

import com.sopian.binarnetworking.data.source.remote.response.CarResponse
import com.sopian.binarnetworking.model.Car

object DataMapper {
    fun mapResponsesToModel(input: List<CarResponse>?): List<Car>? =
        input?.map {
            Car(
                it.id,
                it.name,
                it.category,
                it.image,
                it.price
            )
        }
}