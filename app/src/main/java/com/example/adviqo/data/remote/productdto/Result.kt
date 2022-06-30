package com.example.adviqo.data.remote.productdto

import com.example.adviqo.domain.model.Product
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val id: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String,
)

fun Result.toProduct() : Product {
    return Product(
        id = id,
        price = price,
        thumbnail = thumbnail,
        title = title,
    )
}