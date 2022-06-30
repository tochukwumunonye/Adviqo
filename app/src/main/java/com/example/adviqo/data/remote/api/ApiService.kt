package com.example.adviqo.data.remote.api

import com.example.adviqo.data.remote.productdto.Catalogue
import com.example.adviqo.data.remote.productitemdto.CatalogueItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/sites/MLU/search/")
    suspend fun getProducts(
        @Query("q") word: String,
    ): Catalogue

    @GET("/items/{id}/")
    suspend fun getProductDetails(
        @Path("id") id: String,
    ): CatalogueItem

    companion object{
         const val BASE_URL = "https://api.mercadolibre.com/"
    }
}
