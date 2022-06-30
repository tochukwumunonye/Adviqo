package com.example.adviqo.presentation.productList

import com.example.adviqo.data.repository.ProductRepositoryImplementation
import com.example.adviqo.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ProductListViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var productListViewModel: ProductListViewModel
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
    fun `Given products are loaded When data source returns success Then emit success view state`() {
        val expectedProducts = getListOfProduct()
        runTest {
            whenever(mockProductRepository.searchProduct("books")).thenReturn(expectedProducts)
            productListViewModel = ProductListViewModel(mockProductRepository)
             productListViewModel.search("books")
            val stateFlow = productListViewModel.viewState.value
            assertEquals(stateFlow, ProductListViewState.Success(expectedProducts))
        }
    }

    @Test
    fun `When data source returns error Then emit error view state`() {
        runTest {
            whenever(mockProductRepository.searchProduct("book")).thenThrow(RuntimeException(""))
            productListViewModel = ProductListViewModel(mockProductRepository)
            productListViewModel.search("book")
            val stateFlow = productListViewModel.viewState.value
            assertEquals(stateFlow, ProductListViewState.Error(""))
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
