package com.example.adviqo.domain.model

import com.google.gson.annotations.SerializedName

class ProductItem(
    val date_created: String,
    val id: String,
    val initial_quantity: Int,
    val international_delivery_mode: String,
    val last_updated: String,
    val listing_source: String,
    val permalink: String,
)