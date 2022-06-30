package com.example.adviqo.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adviqo.domain.model.Product
import com.example.adviqo.domain.repository.ProductRepositoryDomain
import com.example.adviqo.presentation.history.ProductHistoryViewState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductHistoryViewModel @Inject constructor(
    private val repository: ProductRepositoryDomain
): ViewModel() {

    private val _viewState = MutableStateFlow<ProductHistoryViewState>(Success(emptyList()))

    val viewState: StateFlow<ProductHistoryViewState> = _viewState

    fun getVisitedProducts() = viewModelScope.launch {
        _viewState.value = Success(repository.retrieveProductsFromDatabase())
    }
}

sealed class ProductHistoryViewState {
    data class Success(val product: List<Product>) : ProductHistoryViewState()
}
