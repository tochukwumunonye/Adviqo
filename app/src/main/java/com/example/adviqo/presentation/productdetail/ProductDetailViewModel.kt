package com.example.adviqo.presentation.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adviqo.domain.model.ProductItem
import com.example.adviqo.domain.repository.ProductRepositoryDomain
import com.example.adviqo.presentation.productList.ProductListFragment
import com.example.adviqo.presentation.productList.ProductListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import com.example.adviqo.presentation.productdetail.ProductDetailViewState.EmptyState
import com.example.adviqo.presentation.productdetail.ProductDetailViewState.Loading
import com.example.adviqo.presentation.productdetail.ProductDetailViewState.Error
import com.example.adviqo.presentation.productdetail.ProductDetailViewState.Success
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
/**
 * ViewModel for [ProductDetailFragment]
 */

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductRepositoryDomain
): ViewModel() {

    private val _viewState = MutableStateFlow<ProductDetailViewState>(EmptyState)

    val viewState: StateFlow<ProductDetailViewState> = _viewState

    fun getDetails(id: String) {
        _viewState.value = Loading
        viewModelScope.launch {
            runCatching {
                repository.getProductDetails(id)
            }.onFailure {
                _viewState.value = Error(it.localizedMessage ?: " An error occurred")
            }.onSuccess {productItem ->
                _viewState.value = Success(productItem)
            }
        }
    }
}

sealed class ProductDetailViewState {
    object EmptyState : ProductDetailViewState()
    object Loading : ProductDetailViewState()
    data class Error(val error: String) : ProductDetailViewState()
    data class Success(val product: ProductItem) : ProductDetailViewState()
}
