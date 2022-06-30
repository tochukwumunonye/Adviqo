package com.example.adviqo.data.repository

import com.example.adviqo.data.local.ProductDao
import com.example.adviqo.data.local.entities.toProduct
import com.example.adviqo.data.remote.api.ApiService
import com.example.adviqo.data.remote.productdto.toProduct
import com.example.adviqo.data.remote.productitemdto.toProductItem
import com.example.adviqo.domain.model.Product
import com.example.adviqo.domain.model.ProductItem
import com.example.adviqo.domain.model.toProductEntity
import com.example.adviqo.domain.repository.ProductRepositoryDomain
import javax.inject.Inject

class ProductRepositoryImplementation @Inject constructor(
    private val api: ApiService,
    private val dao: ProductDao
): ProductRepositoryDomain{

    override suspend fun searchProduct(query: String): List<Product> {
        return api.getProducts(query).results.map{it.toProduct()}
    }

    override suspend fun getProductDetails(word: String): ProductItem {
        return api.getProductDetails(word).toProductItem()
    }

    override suspend fun insertIntoDatabase(product: Product) {
        dao.insertProduct(product.toProductEntity())
    }

    override suspend fun retrieveProductsFromDatabase(): List<Product> {
       return dao.returnLastProducts().map{it.toProduct()}
    }
}
