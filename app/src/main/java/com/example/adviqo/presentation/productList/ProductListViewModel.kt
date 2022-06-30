package com.example.adviqo.presentation.productList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adviqo.domain.model.Product
import com.example.adviqo.domain.repository.ProductRepositoryDomain
import com.example.adviqo.presentation.productList.ProductListViewState.Success
import com.example.adviqo.presentation.productList.ProductListViewState.Error
import com.example.adviqo.presentation.productList.ProductListViewState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for [ProductListFragment]
 */

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepositoryDomain
): ViewModel(){

    private val _viewState = MutableStateFlow<ProductListViewState>(Success(emptyList()))

    val viewState: StateFlow<ProductListViewState> = _viewState

    fun search(query: String) {
        _viewState.value = Loading

        viewModelScope.launch {
            runCatching {
                repository.searchProduct(query)
            }.onFailure { error ->
                _viewState.value = Error(error.localizedMessage ?: "An error occurred")
            }.onSuccess { products ->
                _viewState.value = Success(products)
            }
        }
    }

    fun insertProductInDatabase(product: Product) = viewModelScope.launch {
        repository.insertIntoDatabase(product)
    }
}

sealed class ProductListViewState {
    object Loading : ProductListViewState()
    data class Error(val error: String) : ProductListViewState()
    data class Success(val product: List<Product>) : ProductListViewState()
}
