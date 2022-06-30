package com.example.adviqo.data.repository

import com.example.adviqo.data.local.ProductDao
import com.example.adviqo.data.remote.api.ApiService
import com.example.adviqo.data.remote.productdto.Catalogue
import com.example.adviqo.data.remote.productdto.Result
import com.example.adviqo.data.remote.productitemdto.CatalogueItem
import com.example.adviqo.data.remote.productitemdto.toProductItem
import com.example.adviqo.domain.model.Product
import com.example.adviqo.domain.model.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ProductRepositoryImplementationTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val api: ApiService = mock()
    private val dao: ProductDao = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when search for a product return list of products`() {
        val product = getListOfProduct()
        val catalogue = getResponseFromAPI()
        runTest {
            whenever(api.getProducts("books")).thenReturn(catalogue)
            val productRepo = ProductRepositoryImplementation(api, dao)
            val productList = productRepo.searchProduct("books")
            assertEquals(product, productList)
        }
    }

    @Test
    fun `get details of product`() {
        val productItem = getProductDetails()
        val catalogueItem = getProductDetailsFromAPI()
        runTest {
            whenever(api.getProductDetails("MLU479")).thenReturn(catalogueItem)
            val productDetailRepo = ProductRepositoryImplementation(api,dao)
            val productDetail = productDetailRepo.getProductDetails("MLU479")
            assertEquals(productItem.id, productDetail.id)
            assertEquals(productItem.date_created, productDetail.date_created)
            assertEquals(productItem.last_updated, productDetail.last_updated)
            assertEquals(productItem.initial_quantity, productDetail.initial_quantity)
        }
    }

    private fun  getProductDetails(): ProductItem {
        return ProductItem(
            date_created = "2021",
            id = "MLU479",
            initial_quantity = 2,
            international_delivery_mode = "none",
            last_updated = "2022",
            listing_source = "http://articulo",
            permalink = "10"
        )
    }

    private fun  getProductDetailsFromAPI(): CatalogueItem {
        return CatalogueItem(
            date_created = "2021",
            id = "MLU479",
            initial_quantity = 2,
            international_delivery_mode = "none",
            last_updated = "2022",
            listing_source = "http://articulo",
            permalink = "10"
        )
    }

    private fun getListOfProduct(): List<Product> {
        return listOf(
            Product(
                id = "book1",
                price = 2.3,
                thumbnail = "https://book.com",
                title = "macBook pro"
            ),
            Product(
                id = "book2",
                price = 2.5,
                thumbnail = "https://book2.com",
                title = "macBook pro2"
            )
        )
    }

    private fun getResponseFromAPI(): Catalogue {
        return Catalogue(
            getListOfResultFromApi()
        )
    }

    private fun getListOfResultFromApi(): List<Result> {
        return listOf(
            Result(
                id = "book1",
                price = 2.3,
                thumbnail = "https://book.com",
                title = "macBook pro"
            ),
            Result(
                id = "book2",
                price = 2.5,
                thumbnail = "https://book2.com",
                title = "macBook pro2"
            )
        )
    }

}
