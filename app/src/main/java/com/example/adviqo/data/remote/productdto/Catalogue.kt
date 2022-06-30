package com.example.adviqo.data.remote.productdto

import com.example.adviqo.domain.model.Product
import com.google.gson.annotations.SerializedName

data class Catalogue(
    @SerializedName("results")
    val results: List<Result>,
)
