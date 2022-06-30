package com.example.adviqo.presentation.productdetail

import com.example.adviqo.data.repository.ProductRepositoryImplementation
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
class ProductDetailViewModelTest {


    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var productDetailViewModel: ProductDetailViewModel
    private val mockProductRepository: ProductRepositoryImplementation = mock()



    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given productItem detail is loaded when data source returns success Them emit success view state`() {
        val expectedDetail = getProductDetails()
        runTest {
            whenever(mockProductRepository.getProductDetails("MLU479")).thenReturn(expectedDetail)
            productDetailViewModel = ProductDetailViewModel(mockProductRepository)
            productDetailViewModel.getDetails("MLU479")
            val stateFlow = productDetailViewModel.viewState.value
            assertEquals(stateFlow, ProductDetailViewState.Success(expectedDetail))
        }
    }


    @Test
    fun `When data source returns error Then emit error view state`() {
        runTest {
            whenever(mockProductRepository.getProductDetails("MLU419")).thenThrow(RuntimeException("Error"))
            productDetailViewModel = ProductDetailViewModel(mockProductRepository)
            productDetailViewModel.getDetails("MLU419")
            val stateFlow = productDetailViewModel.viewState.value
            assertEquals(stateFlow, ProductDetailViewState.Error("Error"))
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
}

