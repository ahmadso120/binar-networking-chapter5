package com.sopian.binarnetworking.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CarResponse(
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
)