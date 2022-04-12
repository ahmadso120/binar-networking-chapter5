package com.sopian.binarnetworking.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    val id: Int,
    val name: String,
    val category: String,
    val image: String,
    val price: Int,
) : Parcelable
