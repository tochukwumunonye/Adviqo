package com.example.adviqo.presentation.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adviqo.R
import com.example.adviqo.databinding.FragmentProductHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductHistoryFragment: Fragment(R.layout.fragment_product_history) {

    val productHistoryAdapter = ProductHistoryAdapter()

    private val viewModel: ProductHistoryViewModel by viewModels()

    private var _binding: FragmentProductHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProductHistoryBinding.bind(view)

        setUpRecyclerView()
        viewModel.getVisitedProducts()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    when(viewState) {
                        is ProductHistoryViewState.Success -> {
                            productHistoryAdapter.submitList(viewState.product)
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.productVisitedRecyclerView.apply {
            adapter = productHistoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = null

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

