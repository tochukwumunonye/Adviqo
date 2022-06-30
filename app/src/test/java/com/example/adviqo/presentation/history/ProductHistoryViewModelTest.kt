package com.example.adviqo.presentation.history

import com.example.adviqo.data.repository.ProductRepositoryImplementation
import com.example.adviqo.domain.model.Product
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
class ProductHistoryViewModelTest {


    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var productHistoryViewModel: ProductHistoryViewModel
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
    fun `Given products are loaded When database returns success Then emit success view state`() {
        val expectedProducts = getListOfProduct()
        runTest {
            whenever(mockProductRepository.retrieveProductsFromDatabase()).thenReturn(expectedProducts)
            productHistoryViewModel = ProductHistoryViewModel(mockProductRepository)
            productHistoryViewModel.getVisitedProducts()
            val stateFlow = productHistoryViewModel.viewState.value
            assertEquals(stateFlow, ProductHistoryViewState.Success(expectedProducts))
        }
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
}
