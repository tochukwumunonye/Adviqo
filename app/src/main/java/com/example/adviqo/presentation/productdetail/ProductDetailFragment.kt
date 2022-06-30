package com.example.adviqo.presentation.productdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.adviqo.R
import com.example.adviqo.databinding.FragmentProductDetailBinding
import com.example.adviqo.domain.model.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailFragment @Inject constructor(): Fragment(R.layout.fragment_product_detail) {

    val viewModel: ProductDetailViewModel by viewModels()

     val args by navArgs<ProductDetailFragmentArgs>()
    private val productDetails: Product by lazy {
        args.selectedProduct
    }

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProductDetailBinding.bind(view)

        viewModel.getDetails(productDetails.id)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {viewState ->
                    when(viewState) {
                        is ProductDetailViewState.Success -> {
                            binding.progressBarDetail.visibility = View.GONE
                            binding.id.text = viewState.product.id
                            binding.dateCreated.text = viewState.product.date_created
                            binding.deliveryMode.text = viewState.product.international_delivery_mode
                            binding.initialQuantity.text = viewState.product.initial_quantity.toString()
                            binding.lastUpdated.text = viewState.product.last_updated
                            binding.listingSource.text = viewState.product.listing_source
                            binding.permalink.text = viewState.product.permalink
                        }
                        is ProductDetailViewState.Error -> {
                            binding.progressBarDetail.visibility = View.GONE
                        }
                        is ProductDetailViewState.Loading -> {
                            binding.progressBarDetail.visibility = View.VISIBLE
                        }
                        else -> {Unit}
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
