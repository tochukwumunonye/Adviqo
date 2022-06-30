package com.example.adviqo.domain.repository

import com.example.adviqo.domain.model.Product
import com.example.adviqo.domain.model.ProductItem

interface ProductRepositoryDomain {

    suspend fun searchProduct(query: String) : List<Product>

    suspend fun getProductDetails(word: String): ProductItem

    suspend fun insertIntoDatabase(product: Product)

    suspend fun retrieveProductsFromDatabase(): List<Product>
}