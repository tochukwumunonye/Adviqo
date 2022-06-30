package com.example.adviqo.data.remote.productitemdto

import com.example.adviqo.data.remote.productdto.Result
import com.example.adviqo.domain.model.Product
import com.example.adviqo.domain.model.ProductItem
import com.google.gson.annotations.SerializedName

data class CatalogueItem(
    @SerializedName("date_created")
    val date_created: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("initial_quantity")
    val initial_quantity: Int,
    @SerializedName("international_delivery_mode")
    val international_delivery_mode: String,
    @SerializedName("last_updated")
    val last_updated: String,
    @SerializedName("listing_source")
    val listing_source: String,
    @SerializedName("permalink")
    val permalink: String,
)

fun CatalogueItem.toProductItem() : ProductItem {
    return ProductItem(
        date_created = date_created,
        id = id,
        initial_quantity = initial_quantity,
        international_delivery_mode = international_delivery_mode,
        last_updated = last_updated,
        listing_source = listing_source,
        permalink = permalink
    )
}