package com.example.adviqo.domain.model

import android.os.Parcelable
import com.example.adviqo.data.local.entities.ProductEntity
import com.example.adviqo.data.remote.productdto.Result
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String,
    val price: Double,
    val thumbnail: String,
    val title: String,
): Parcelable

fun Product.toProductEntity() : ProductEntity {
    return ProductEntity(
        id = id,
        price = price,
        thumbnail = thumbnail,
        title = title,
    )
}